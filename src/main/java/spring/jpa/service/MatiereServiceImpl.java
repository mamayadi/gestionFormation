package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Matiere;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.service.interfaces.MatiereService;

@Service
public class MatiereServiceImpl implements MatiereService {

	@Autowired
	private MatiereRepository matiereRepos;

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

	
}
