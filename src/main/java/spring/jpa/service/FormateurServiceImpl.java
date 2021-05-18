package spring.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;
import spring.jpa.model.Seance;
import spring.jpa.repository.EtudiantRepository;
import spring.jpa.repository.FichePresenceRepository;
import spring.jpa.repository.FormateurRepository;
import spring.jpa.repository.GroupeRepository;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.repository.NoteRepository;
import spring.jpa.repository.SeanceRepository;
import spring.jpa.service.interfaces.FormateurService;

@Service
public class FormateurServiceImpl implements FormateurService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private FormateurRepository formateurRepos;
	@Autowired
	private EtudiantRepository etudiantRepos;
	@Autowired
	private GroupeRepository groupeRepos;
	@Autowired
	private SeanceRepository seanceRepos;
	@Autowired
	private MatiereRepository matiereRepos;
	@Autowired
	private NoteRepository noteRepos;
	@Autowired
	private FichePresenceRepository fichePresenceRepos;

	public FormateurServiceImpl() {
	}

	@Override
	public Formateur createFormateur(Formateur formateur) {
		formateur.setPassword(passwordEncoder.encode(formateur.getPassword()));
		return formateurRepos.save(formateur);
	}

	@Override
	public List<Formateur> getFormateurs() {
		return formateurRepos.findAll();
	}

	@Override
	public Formateur getFormateurById(Long id) {
		return formateurRepos.findById(id).orElseThrow(() -> new NotFoundException("Formateur not found!"));
	}

	@Override
	public Formateur updateFormateur(Long id, Formateur formateur) {
		Formateur foundedFormateur = getFormateurById(id);
		if (formateur.getNom() != null) {
			foundedFormateur.setNom(formateur.getNom());
		}
		if (formateur.getPrenom() != null) {
			foundedFormateur.setPrenom(formateur.getPrenom());
		}
		if (foundedFormateur.getPassword() != null) {
			foundedFormateur.setPassword(passwordEncoder.encode(foundedFormateur.getPassword()));
		}
		return formateurRepos.save(foundedFormateur);
	}

	@Override
	public void deleteFormateur(Long id) {
		Formateur formateur = getFormateurById(id);
		formateurRepos.delete(formateur);
	}

	/******* End formateur CRUD ******/

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

	/******** Formateur Methods *********/
	@Override
	public Formateur addGroupe(Long id, Long idGroupe) {
		Formateur formateur = getFormateurById(id);
		Groupe groupe = groupeRepos.findById(idGroupe).orElseThrow(() -> new NotFoundException("Groupe not found!"));
		formateur.addGroupe(groupe);
		return formateurRepos.save(formateur);
	}

	@Override
	public Formateur addMatiere(Long id, Long idMatiere) {
		Formateur formateur = getFormateurById(id);
		Matiere matiere = matiereRepos.findById(idMatiere)
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		formateur.addMatiere(matiere);
		return formateurRepos.save(formateur);
	}

	@Override
	public Matiere addSeancePourMatiere(Matiere matiere, Seance senace) {
		Matiere foundedMatiere = matiereRepos.findById(matiere.getId())
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		Groupe groupe = groupeRepos.findById(foundedMatiere.getId())
				.orElseThrow(() -> new NotFoundException("Groupe not found!"));
		List<FichePresence> listFichePresence = new ArrayList<FichePresence>();
		for (Etudiant etudiant : groupe.getListEtudiant()) {
			FichePresence fichePresence = fichePresenceRepos.save(new FichePresence(etudiant, false));
			listFichePresence.add(fichePresence);
		}
		senace.setListFichePresence(listFichePresence);
		Seance createdSeance = seanceRepos.save(senace);
		foundedMatiere.setNombreHeureEnseigne(foundedMatiere.getNombreHeureEnseigne() + createdSeance.getDuree());
		foundedMatiere.addSeance(createdSeance);
		return matiereRepos.save(foundedMatiere);
	}

}
