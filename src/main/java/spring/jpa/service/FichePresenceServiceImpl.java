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
import spring.jpa.repository.EtudiantRepository;
import spring.jpa.repository.FichePresenceRepository;
import spring.jpa.repository.GroupeRepository;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.service.interfaces.FichePresenceService;

@Service
public class FichePresenceServiceImpl implements FichePresenceService {
	@Autowired
	private FichePresenceRepository fichePresenceRepos;
	@Autowired
	private EtudiantRepository etudiantRepos;
	@Autowired
	private GroupeRepository groupeRepos;
	@Autowired
	private MatiereRepository matiereRepos;

	public FichePresenceServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public FichePresence createFichePresence(FichePresence fichePresence) {
		return fichePresenceRepos.save(fichePresence);
	}

	@Override
	public List<FichePresence> getFichePresences() {
		return fichePresenceRepos.findAll();
	}

	@Override
	public FichePresence getFichePresenceById(Long id) {
		return fichePresenceRepos.findById(id).orElseThrow(() -> new NotFoundException("Fiche presence not found!"));
	}

	@Override
	public FichePresence updateFichePresence(Long id, FichePresence fichePresence) {
		FichePresence foundedFichePresence = getFichePresenceById(id);
		if (fichePresence.getEtudiant() != null) {
			foundedFichePresence.setEtudiant(fichePresence.getEtudiant());
		}
		if (fichePresence.isPresence()) {
			foundedFichePresence.setPresence(fichePresence.isPresence());
		}
		return fichePresenceRepos.save(foundedFichePresence);
	}

	@Override
	public void deleteFichePresence(Long id) {
		FichePresence fichePresence = getFichePresenceById(id);
		fichePresenceRepos.delete(fichePresence);
	}

	/******** End Fiche presence CRUD ******** */

	@Override
	public double consulterTauxPresenceParMatiere(Long id, Long idMatiere) {
		Etudiant etudiant = etudiantRepos.findById(id).orElseThrow(() -> new NotFoundException("Etudiant not found!"));
		Matiere foundedMatiere = matiereRepos.findById(idMatiere)
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		List<Seance> listSeance = foundedMatiere.getListSeance();
		double total = 0;
		for (Seance seance : listSeance) {
			List<FichePresence> listFiche = seance.getListFichePresence();
			for (FichePresence fichePresence : listFiche) {
				if (fichePresence.isPresence() && fichePresence.getEtudiant().getId() == etudiant.getId()) {
					total += 1;
				}
			}
		}
		return total * 100 / foundedMatiere.getListSeance().size();
	}

	@Override
	public List<FichePresence> consulterHistoriquePresenceParGroupe(Long groupeId) {
		Groupe foundedGroupe = groupeRepos.findById(groupeId)
				.orElseThrow(() -> new NotFoundException("Groupe not found!"));
		List<Matiere> listMatiere = foundedGroupe.getListMatiere();
		List<FichePresence> listFichePresenceFounded = new ArrayList<FichePresence>();
		for (Matiere matiere : listMatiere) {
			List<Seance> listSeance = matiere.getListSeance();
			for (Seance seance : listSeance) {
				List<FichePresence> listFichePresence = seance.getListFichePresence();
				for (FichePresence fichePresence : listFichePresence) {
					listFichePresenceFounded.add(fichePresence);
				}
			}
		}
		return listFichePresenceFounded;
	}

	@Override
	public List<FichePresence> consulterHistoriquePresenceParEtudiant(Long etudiantId) {
		Etudiant foundedEtudiant = etudiantRepos.findById(etudiantId) 
				.orElseThrow(() -> new NotFoundException("Etudiant not found!"));
		List<FichePresence> foundedFichePresence = fichePresenceRepos.findByEtudiant(foundedEtudiant);
		return foundedFichePresence;
	}
}
