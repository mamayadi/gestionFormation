package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.repository.GroupeRepository;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.service.interfaces.GroupeService;

@Service
public class GroupeServiceImpl implements GroupeService {
	@Autowired
	private GroupeRepository groupeRepos;
	@Autowired
	private MatiereRepository matiereRepos;

	public GroupeServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public Groupe createGroupe(Groupe groupe) {
		return groupeRepos.save(groupe);
	}

	public List<Groupe> getGroupes() {
		return groupeRepos.findAll();
	}

	public Groupe getGroupeById(Long id) {
		return groupeRepos.findById(id).orElseThrow(() -> new NotFoundException("Groupe not found!"));
	}

	public Groupe updateGroupe(Long id, Groupe groupe) {
		Groupe foundedGroupe = getGroupeById(id);
		if (groupe.getLibelle() != null) {
			foundedGroupe.setLibelle(groupe.getLibelle());
		}
		if (groupe.getListEtudiant() != null) {
			foundedGroupe.setListEtudiant(groupe.getListEtudiant());
		}
		if (groupe.getListMatiere() != null) {
			foundedGroupe.setListMatiere(groupe.getListMatiere());
		}
		return groupeRepos.save(foundedGroupe);
	}

	public void deleteGroupe(Long id) {
		Groupe groupe = getGroupeById(id);
		groupeRepos.delete(groupe);
	}

	/****** Groupe end CRUD ********/

	@Override
	public Groupe assignerMatiereAuGroupe(Long idGroupe, Long idMatiere) {
		Groupe foundedGroupe = groupeRepos.findById(idGroupe)
				.orElseThrow(() -> new NotFoundException("Groupe not found!"));
		Matiere foundedMatiere = matiereRepos.findById(idMatiere)
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		foundedGroupe.addMatiere(foundedMatiere);
		foundedMatiere.setGroupe(foundedGroupe);
		matiereRepos.save(foundedMatiere);
		return groupeRepos.save(foundedGroupe);
	}

}
