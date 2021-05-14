package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;
import spring.jpa.model.Seance;
import spring.jpa.repository.EtudiantRepository;
import spring.jpa.repository.FormateurRepository;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.repository.NoteRepository;
import spring.jpa.repository.SeanceRepository;
import spring.jpa.service.interfaces.FormateurService;

@Service
public class FormateurServiceImpl implements FormateurService {
	@Autowired
	private FormateurRepository formateurRepos;
	@Autowired
	private EtudiantRepository etudiantRepos;
	@Autowired
	private SeanceRepository seanceRepos;
	@Autowired
	private MatiereRepository matiereRepos;
	@Autowired
	private NoteRepository noteRepos;

	public FormateurServiceImpl() {
	}

	@Override
	public Formateur createFormateur(Formateur formateur) {
		return formateurRepos.save(formateur);
	}

	@Override
	public List<Formateur> getFormateurs() {
		return formateurRepos.findAll();
	}

	@Override
	public Formateur getFormateurById(Long id) {
		return formateurRepos.findById(id).get();
	}

	@Override
	public Formateur updateFormateur(Long id, Formateur formateur) {
		Formateur foundedFormateur = formateurRepos.findById(id).get();
		foundedFormateur.setNom(formateur.getNom());
		foundedFormateur.setPrenom(formateur.getPrenom());
		return formateurRepos.save(foundedFormateur);
	}

	@Override
	public void deleteFormateur(Long id) {
		formateurRepos.deleteById(id);

	}

	@Override
	public Matiere addSeancePourMatiere(Matiere matiere, Seance senace) {
		Matiere foundedMatiere = matiereRepos.findById(matiere.getId()).get();
		Seance createdSeance = seanceRepos.save(senace);
		foundedMatiere.addSeance(createdSeance);
		return matiereRepos.save(foundedMatiere);
	}

	@Override
	public Note addNote(Matiere matiere, Etudiant etudiant, Note note) {
		note.setEtudiant(etudiant);
		note.setMatiere(matiere);
		Note savedNote = noteRepos.save(note);
		etudiant.addNote(savedNote);
		etudiantRepos.save(etudiant);
		return savedNote;

	}

	@Override
	public Note affecterNoteDC(Matiere matiere, Etudiant etudiant, double noteDC) {
		Note foundedNote = null;
		for (Note note : etudiant.getListNote()) {
			if (note.getMatiere().getId() == matiere.getId()) {
				note.setNoteDC(noteDC);
				foundedNote = noteRepos.save(note);
			}
		}
		return foundedNote;

	}

	@Override
	public Note affecterNoteDS(Matiere matiere, Etudiant etudiant, double noteDS) {
		Note foundedNote = null;
		for (Note note : etudiant.getListNote()) {
			if (note.getMatiere().getId() == matiere.getId()) {
				note.setNoteDC(noteDS);
				foundedNote = noteRepos.save(note);
			}
		}
		return foundedNote;
	}

	@Override
	public Seance affecterFichePresencePourSeance(Seance seance, List<FichePresence> listFichePresence) {
		Seance foundedSeance = seanceRepos.findById(seance.getId())
				.orElseThrow(() -> new NotFoundException("Seance not found!"));
		foundedSeance.setListFichePresence(listFichePresence);
		return seanceRepos.save(foundedSeance);

	}

	@Override
	public List<Seance> consulterListSeancePourGroupeMatiere(Matiere matiere) {
		Matiere foundedMatiere = matiereRepos.findById(matiere.getId())
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		return foundedMatiere.getListSeance();

	}

}
