package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Etudiant;
import spring.jpa.model.Groupe;
import spring.jpa.model.Note;
import spring.jpa.repository.EtudiantRepository;
import spring.jpa.repository.GroupeRepository;
import spring.jpa.repository.NoteRepository;
import spring.jpa.service.interfaces.EtudiantService;

@Service
public class EtudiantServiceImpl implements EtudiantService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EtudiantRepository etudiantRepos;
	@Autowired
	private GroupeRepository groupeRepos;
	@Autowired
	private NoteRepository noteRepos;

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
	public Etudiant assignerGroupeAuEtudiant(Long groupeId, Long etudiantId) {
		Groupe foundedGroupe = groupeRepos.findById(groupeId)
				.orElseThrow(() -> new NotFoundException("Groupe not found!"));
		Etudiant foundedEtudiant = getEtudiantById(etudiantId);
		foundedEtudiant.setGroupe(foundedGroupe);
		foundedGroupe.addEtudiant(foundedEtudiant);
		groupeRepos.save(foundedGroupe);
		return etudiantRepos.save(foundedEtudiant);
	}

	@Override
	public Etudiant ajouterNoteAuEtudiant(Note note, Long etudiantId) {
		Etudiant foundedEtudiant = getEtudiantById(etudiantId);
		Note savedNote = noteRepos.save(note);
		foundedEtudiant.addNote(savedNote);
		return etudiantRepos.save(foundedEtudiant);
	}
}
