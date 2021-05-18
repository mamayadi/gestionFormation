package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Note;
import spring.jpa.repository.NoteRepository;
import spring.jpa.service.interfaces.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepos;

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
}
