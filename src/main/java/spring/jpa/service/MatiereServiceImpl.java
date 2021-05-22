package spring.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.model.Seance;
import spring.jpa.repository.FichePresenceRepository;
import spring.jpa.repository.GroupeRepository;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.repository.SeanceRepository;
import spring.jpa.service.interfaces.MatiereService;

@Service
public class MatiereServiceImpl implements MatiereService {

	@Autowired
	private MatiereRepository matiereRepos;
	@Autowired
	private SeanceRepository seanceRepos;
	@Autowired
	private GroupeRepository groupeRepos;
	@Autowired
	private FichePresenceRepository fichePresenceRepos;

	public MatiereServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public Matiere createMatiere(Matiere matiere) {
		return matiereRepos.save(matiere);
	}

	public List<Matiere> getMatieres() {
		return matiereRepos.findAll();
	}

	public Matiere getMatiereById(Long id) {
		return matiereRepos.findById(id).orElseThrow(() -> new NotFoundException("Matiere not found!"));
	}

	public Matiere updateMatiere(Long id, Matiere matiere) {
		Matiere foundedMatiere = getMatiereById(id);
		if (matiere.getLibelle() != null) {
			foundedMatiere.setLibelle(matiere.getLibelle());
		}
		if (matiere.getGroupe() != null) {
			foundedMatiere.setGroupe(matiere.getGroupe());
		}
		if (matiere.getListSeance() != null) {
			foundedMatiere.setListSeance(matiere.getListSeance());
		}
		if (matiere.getNombreHeureEnseigne() != null) {
			foundedMatiere.setNombreHeureEnseigne(matiere.getNombreHeureEnseigne());
		}
		if (matiere.getVolumeHoraire() != null) {
			foundedMatiere.setVolumeHoraire(matiere.getVolumeHoraire());
		}
		return matiereRepos.save(foundedMatiere);
	}

	public void deleteMatiere(Long id) {
		Matiere matiere = getMatiereById(id);
		matiereRepos.delete(matiere);
	}

	/******* Matiere end CRUD ********/

	@Override
	public List<Matiere> getListMatiereParGroupe(Long idGroupe) {
		Groupe groupe = groupeRepos.findById(idGroupe).orElseThrow(() -> new NotFoundException("Groupe not found!"));
		return groupe.getListMatiere();
	}

	@Override
	public Matiere ajoutSeancePourMatiere(Long idMatiere, Seance seance) {
		Matiere foundedMatiere = getMatiereById(idMatiere);
		Groupe groupe = groupeRepos.findById(foundedMatiere.getId())
				.orElseThrow(() -> new NotFoundException("Groupe not found!"));
		List<FichePresence> listFichePresence = new ArrayList<FichePresence>();
		for (Etudiant etudiant : groupe.getListEtudiant()) {
			FichePresence fichePresence = fichePresenceRepos.save(new FichePresence(etudiant, false));
			listFichePresence.add(fichePresence);
		}
		seance.setListFichePresence(listFichePresence);
		Seance createdSeance = seanceRepos.save(seance);
		foundedMatiere.setNombreHeureEnseigne(foundedMatiere.getNombreHeureEnseigne() + createdSeance.getDuree());
		foundedMatiere.addSeance(createdSeance);
		return matiereRepos.save(foundedMatiere);
	}

}
