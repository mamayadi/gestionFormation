package spring.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;
import spring.jpa.model.Seance;
import spring.jpa.repository.EtudiantRepository;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.service.interfaces.EtudiantService;

@Service
public class EtudiantServiceImpl implements EtudiantService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EtudiantRepository etudiantRepos;
	@Autowired
	private MatiereRepository matiereRepos;

	public EtudiantServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Etudiant createEtudiant(Etudiant etudiant) {
		etudiant.setPassword(passwordEncoder.encode(etudiant.getPassword()));
		return etudiantRepos.save(etudiant);
	}

	@Override
	public List<Etudiant> getEtudiants() {
		return etudiantRepos.findAll();
	}

	@Override
	public Etudiant getEtudiantById(Long id) {
		return etudiantRepos.findById(id).orElseThrow(() -> new NotFoundException("Etudiant not found!"));
	}

	@Override
	public Etudiant updateEtudiant(Long id, Etudiant etudiant) {
		Etudiant foundedEtudiant = getEtudiantById(id);
		foundedEtudiant.setNom(etudiant.getNom());
		foundedEtudiant.setPrenom(etudiant.getPrenom());
		foundedEtudiant.setPassword(passwordEncoder.encode(etudiant.getPassword()));
		if (etudiant.getGroupe() != null) {
			foundedEtudiant.setGroupe(etudiant.getGroupe());
		}
		if (etudiant.getListNote() != null && etudiant.getListNote().size() > 0) {
			foundedEtudiant.setListNote(etudiant.getListNote());
		}
		return etudiantRepos.save(foundedEtudiant);
	}

	@Override
	public void deleteEtudiant(Long id) {
		Etudiant etudiant = getEtudiantById(id);
		etudiantRepos.delete(etudiant);
	}

	/******* Etudiant end CRUD *******/

	@Override
	public Note consulterNoteEtMoyenneParMatiere(Etudiant etudiant, Matiere matiere) {
		Etudiant foundedEtudiant = getEtudiantById(etudiant.getId());
		List<Note> listNote = foundedEtudiant.getListNote();
		Note foundedNote = null;
		for (Note note : listNote) {
			if (note.getMatiere().getId() == matiere.getId()) {
				foundedNote = note;
				break;
			}
		}
		return foundedNote;
	}

	@Override
	public double consulterTauxPresenceParMatiere(Long id, Matiere matiere) {
		Etudiant etudiant = getEtudiantById(id);
		Matiere foundedMatiere = matiereRepos.findById(matiere.getId())
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		List<Seance> listSeance = foundedMatiere.getListSeance();
		double total = 0;
		//List<FichePresence> foundedFicheList = new ArrayList<FichePresence>();
		for (Seance seance : listSeance) {
			List<FichePresence> listFiche = seance.getListFichePresence();
			for (FichePresence fichePresence : listFiche) {
				if (fichePresence.isPresence() && fichePresence.getEtudiant().getId() == etudiant.getId()) {
					total+=1;
					//foundedFicheList.add(fichePresence);
				}
			}
		}
		return total *100/foundedMatiere.getListSeance().size();
	}

}
