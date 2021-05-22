package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Formateur;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.repository.FormateurRepository;
import spring.jpa.repository.GroupeRepository;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.service.interfaces.FormateurService;

@Service
public class FormateurServiceImpl implements FormateurService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private FormateurRepository formateurRepos;
	@Autowired
	private GroupeRepository groupeRepos;
	@Autowired
	private MatiereRepository matiereRepos;

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
	public Formateur assignerGroupeAuFormateur(Long idFormateur, Long idGroupe) {
		Formateur foundedFormateur = getFormateurById(idFormateur);
		Groupe foundedGroupe = groupeRepos.findById(idGroupe)
				.orElseThrow(() -> new NotFoundException("Groupe not found!"));
		foundedFormateur.addGroupe(foundedGroupe);
		return formateurRepos.save(foundedFormateur);
	}

	@Override
	public Formateur assignerMatiereAuFormateur(Long idFormateur, Long idMatiere) {
		Formateur foundedFormateur = getFormateurById(idFormateur);
		Matiere foundedMatiere = matiereRepos.findById(idMatiere)
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		foundedFormateur.addMatiere(foundedMatiere);
		return formateurRepos.save(foundedFormateur);
	}

}
