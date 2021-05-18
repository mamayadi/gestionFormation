package spring.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.jpa.model.Admin;
import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;
import spring.jpa.model.Seance;
import spring.jpa.service.interfaces.AdminService;
import spring.jpa.service.interfaces.EtudiantService;
import spring.jpa.service.interfaces.FichePresenceService;
import spring.jpa.service.interfaces.FormateurService;
import spring.jpa.service.interfaces.GroupeService;
import spring.jpa.service.interfaces.MatiereService;
import spring.jpa.service.interfaces.NoteService;
import spring.jpa.service.interfaces.SeanceService;

@Service
public class InitDb implements CommandLineRunner {
	@Autowired
	AdminService adminService;
	@Autowired
	EtudiantService etudiantService;
	@Autowired
	FichePresenceService fichePresenceService;
	@Autowired
	FormateurService formateurService;
	@Autowired
	GroupeService groupeService;
	@Autowired
	MatiereService matiereService;
	@Autowired
	NoteService noteService;
	@Autowired
	SeanceService seanceService;

	@Override
	public void run(String... args) throws Exception {
		try {
			Admin admin = adminService.createAdmin(new Admin("super", "admin", "admin", "123"));
			Etudiant etudiant = etudiantService.createEtudiant(new Etudiant("med", "ayadi", "medayadi", "123"));
			Formateur formateur = formateurService.createFormateur(new Formateur("ahmed", "jmal", "jmalahmed", "123"));
			Groupe groupe = groupeService.createGroupe(new Groupe("GINF 2.2"));
			Matiere matiere = matiereService.createMatiere(new Matiere(40, "Spring"));
			matiere.setGroupe(groupe);
			matiere = matiereService.updateMatiere(matiere.getId(), matiere);
			Seance seance = seanceService.createSeance(new Seance(new Date(), 3.0, matiere, "Seance description test"));
			Note note = noteService.createNote(new Note(16.0, 17.0));
			note.setEtudiant(etudiant);
			note.setMatiere(matiere);
			note = noteService.updateNote(note.getId(), note);
			FichePresence fichePresence = fichePresenceService.createFichePresence(new FichePresence(etudiant, true));
			List<FichePresence> listFichePresence = new ArrayList<FichePresence>();
			listFichePresence.add(fichePresence);

			// Admin : Formateur
			adminService.assignerGroupeAuFormateur(groupe.getId(), formateur.getId());
			adminService.assignerMatiereAuFormateur(matiere.getId(), formateur.getId());
			adminService.assignerGroupeAuEtudiant(groupe.getId(), etudiant.getId());
			adminService.assignerMatiereAuGroupe(matiere.getId(), groupe.getId());
			adminService.ajoutFichePresenceAuSeance(fichePresence, seance.getId());
			adminService.ajouterNoteAuEtudiant(note, etudiant.getId());
			//adminService.ajoutSeancePourMatiere(seance, matiere.getId());

			// Formateur
//			formateurService.addSeancePourMatiere(matiere, seance);
//			formateurService.affecterFichePresencePourSeance(seance, listFichePresence);
//			formateurService.addNote(matiere, etudiant, note);

			// Etudiant

			// Formateur
//			formateur.addGroupe(groupe);
//			formateur.addMatiere(matiere);

			// Groupe
//			groupe.addEtudiant(etudiant);
//			groupe.addMatiere(matiere);
			// Seance

			// Matiere
//			matiere.addSeance(seance);

			// Note

//			note.setEtudiant(etudiant);
//			note.setMatiere(matiere);

			// Fiche Présence

		} catch (Exception e) {
			throw (e);
		}

	}

}
