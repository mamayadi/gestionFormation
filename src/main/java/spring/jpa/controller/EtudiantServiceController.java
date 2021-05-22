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
import spring.jpa.enums.DeleteResponse;
import spring.jpa.model.Etudiant;
import spring.jpa.model.Note;
import spring.jpa.service.interfaces.EtudiantService;

@RestController
@RequestMapping(path = "/etudiants")
@SecurityRequirement(name = "JwtAuthentication")
public class EtudiantServiceController {

	@Autowired
	EtudiantService etudiantService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Etudiant>> getEtudiants() {
		return new ResponseEntity<List<Etudiant>>(etudiantService.getEtudiants(), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant) {
		return new ResponseEntity<Etudiant>(etudiantService.createEtudiant(etudiant), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Etudiant> updateEtudiant(@PathVariable("id") Long id, @RequestBody Etudiant etudiant) {
		return new ResponseEntity<Etudiant>(etudiantService.updateEtudiant(id, etudiant), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Etudiant> getEtudiant(@PathVariable("id") Long id) {
		return new ResponseEntity<Etudiant>(etudiantService.getEtudiantById(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteEtudiant(@PathVariable("id") Long id) {
		etudiantService.deleteEtudiant(id);
		return new ResponseEntity<>(new DeleteResponse("Etudiant is deleted successfully"), HttpStatus.OK);
	}

	/*******************/
	@PostMapping(value = "/{etudiantId}/add-groupe/{groupeId}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Etudiant> addGroupeEtudiant(@PathVariable("etudiantId") Long etudiantId,
			@PathVariable("groupeId") Long groupeId) {
		return new ResponseEntity<Etudiant>(etudiantService.assignerGroupeAuEtudiant(groupeId, etudiantId),
				HttpStatus.OK);
	}

	@PostMapping(value = "/{etudiantId}/add-note", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Etudiant> addNoteEtudiant(@PathVariable("etudiantId") Long etudiantId,
			@RequestBody Note note) {
		return new ResponseEntity<Etudiant>(etudiantService.ajouterNoteAuEtudiant(note, etudiantId), HttpStatus.OK);
	}

}
