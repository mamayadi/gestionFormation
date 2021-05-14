package spring.jpa.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import spring.jpa.model.Admin;
import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;
import spring.jpa.model.Seance;
import spring.jpa.service.interfaces.AdminService;

@RestController
@RequestMapping(path = "/admins")
@SecurityRequirement(name = "JwtAuthentication")
public class AdminServiceController {
	@Autowired
	AdminService adminService;

	public AdminServiceController() {
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Admin>> getAdmins() {
		return new ResponseEntity<List<Admin>>(adminService.getAdmins(), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
		return new ResponseEntity<Admin>(adminService.createAdmin(admin), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Long id, @RequestBody Admin admin) {
		return new ResponseEntity<Admin>(adminService.updateAdmin(id, admin), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Admin> getAdmin(@PathVariable("id") Long id) {
		return new ResponseEntity<Admin>(adminService.getAdminById(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id) {
		adminService.deleteAdmin(id);
		return new ResponseEntity<>("Admin is deleted successfully", HttpStatus.OK);
	}

	/*********************/
	@PostMapping(value = "/{idGroupe}/assigne-groupe-formateur/{idFormateur}")
	public ResponseEntity<Formateur> assignerGroupeAuFormateur(@PathVariable("idGroupe") Long idGroupe,
			@PathVariable("idFormateur") Long idFormateur) {
		return new ResponseEntity<Formateur>(adminService.assignerGroupeAuFormateur(idGroupe, idFormateur),
				HttpStatus.OK);
	}

	@PostMapping(value = "/{idMatiere}/assigne-matiere-formateur/{idFormateur}")
	public ResponseEntity<Formateur> assignerMatiereAuFormateur(@PathVariable("idMatiere") Long idMatiere,
			@PathVariable("idFormateur") Long idFormateur) {
		return new ResponseEntity<Formateur>(adminService.assignerMatiereAuFormateur(idMatiere, idFormateur),
				HttpStatus.OK);
	}

	@PostMapping(value = "/{idGroupe}/assigne-groupe-etudiant/{etudiantId}")
	public ResponseEntity<Etudiant> assignerGroupeAuEtudiant(@PathVariable("idGroupe") Long idGroupe,
			@PathVariable("etudiantId") Long etudiantId) {
		return new ResponseEntity<Etudiant>(adminService.assignerGroupeAuEtudiant(idGroupe, etudiantId), HttpStatus.OK);
	}

	@PostMapping(value = "/{idMatiere}/assigne-matiere-groupe/{idGroupe}")
	public ResponseEntity<Groupe> assignerMatiereAuGroupe(@PathVariable("idMatiere") Long idMatiere,
			@PathVariable("idGroupe") Long idGroupe) {
		return new ResponseEntity<Groupe>(adminService.assignerMatiereAuGroupe(idMatiere, idGroupe), HttpStatus.OK);
	}

	@PostMapping(value = "/{idMatiere}/ajout-seance-matiere")
	public ResponseEntity<Matiere> ajoutSeancePourMatiere(@PathVariable("idMatiere") Long idMatiere,
			@RequestBody Seance seance) {
		return new ResponseEntity<Matiere>(adminService.ajoutSeancePourMatiere(seance, idMatiere), HttpStatus.OK);
	}

	@PostMapping(value = "/{idEtudiant}/ajout-note-etudiant")
	public ResponseEntity<Etudiant> ajouterNoteAuEtudiant(@PathVariable("idEtudiant") Long idEtudiant,
			@RequestBody Note note) {
		return new ResponseEntity<Etudiant>(adminService.ajouterNoteAuEtudiant(note, idEtudiant), HttpStatus.OK);
	}

	@PostMapping(value = "/{idSeance}/ajout-fiche-seance")
	public ResponseEntity<Seance> ajoutFichePresenceAuSeance(@PathVariable("idSeance") Long idSeance,
			@RequestBody FichePresence fichePresence) {
		return new ResponseEntity<Seance>(adminService.ajoutFichePresenceAuSeance(fichePresence, idSeance),
				HttpStatus.OK);
	}

	@GetMapping(value = "/{groupeId}/historique-presence-par-groupe")
	public ResponseEntity<List<FichePresence>> consulterHistoriquePresenceParGroupe(
			@PathVariable("groupeId") Long groupeId) {
		return new ResponseEntity<List<FichePresence>>(adminService.consulterHistoriquePresenceParGroupe(groupeId),
				HttpStatus.OK);
	}

	@GetMapping(value = "/{etudiantId}/historique-presence-par-etudiant")
	public ResponseEntity<List<FichePresence>> consulterHistoriquePresenceParEtudiant(
			@PathVariable("etudiantId") Long etudiantId) {
		return new ResponseEntity<List<FichePresence>>(adminService.consulterHistoriquePresenceParEtudiant(etudiantId),
				HttpStatus.OK);
	}

}
