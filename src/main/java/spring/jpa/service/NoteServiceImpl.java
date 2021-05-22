package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Etudiant;
import spring.jpa.model.Note;
import spring.jpa.repository.EtudiantRepository;
import spring.jpa.repository.NoteRepository;
import spring.jpa.service.interfaces.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepos;
	@Autowired
	private EtudiantRepository etudiantRepos;

	public NoteServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public Note createNote(Note note) {
		return noteRepos.save(note);
	}

	public List<Note> getNotes() {
		return noteRepos.findAll();
	}

	public Note getNoteById(Long id) {
		return noteRepos.findById(id).orElseThrow(() -> new NotFoundException("Note not found!"));
	}

	public Note updateNote(Long id, Note note) {
		Note foundedNote = getNoteById(id);
		if (note.getEtudiant() != null) {
			foundedNote.setEtudiant(note.getEtudiant());
		}
		if (note.getMatiere() != null) {
			foundedNote.setMatiere(note.getMatiere());
		}
		if (note.getNoteDC() != null) {
			foundedNote.setNoteDC(note.getNoteDC());
		}
		if (note.getNoteDS() != null) {
			foundedNote.setNoteDS(note.getNoteDS());
		}
		return noteRepos.save(foundedNote);
	}

	public void deleteNote(Long id) {
		Note note = getNoteById(id);
		noteRepos.delete(note);
	}

	/****** End Note CRUD ***** */

	@Override
	public Note addUpdateNote(Note note) {
		Note foundedNote = null;
		if(note.getId() != 0) {
			foundedNote = getNoteById(note.getId());
			foundedNote.setNoteDC(note.getNoteDC());
			foundedNote.setNoteDS(note.getNoteDS());
			foundedNote.setMatiere(note.getMatiere());
			foundedNote.setEtudiant(note.getEtudiant());
		} else {
			foundedNote = note;
		}
		Note savedNote = noteRepos.save(foundedNote);
		Etudiant etudiant = etudiantRepos.findById(savedNote.getEtudiant().getId())
				.orElseThrow(() -> new NotFoundException("Etudiant not found!"));
		etudiant.addNote(savedNote);
		etudiantRepos.save(etudiant);
		return savedNote;

	}	

	@Override
	public Note consulterMoyenneEtudiantParMatiere(Long idEtudiant, Long idMatiere) {
		Etudiant foundedEtudiant = etudiantRepos.findById(idEtudiant)
				.orElseThrow(() -> new NotFoundException("Etudiant not found!"));
		List<Note> listNote = foundedEtudiant.getListNote();
		Note foundedNote = null;
		for (Note note : listNote) {
			if (note.getMatiere().getId() == idMatiere) {
				foundedNote = note;
				break;
			}
		}
		return foundedNote;
	}
}
