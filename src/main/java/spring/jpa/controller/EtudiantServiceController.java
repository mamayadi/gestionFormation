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

import spring.jpa.model.Etudiant;
import spring.jpa.service.interfaces.EtudiantService;

@RestController
@RequestMapping(path = "/etudiants")
public class EtudiantServiceController {

	@Autowired
	EtudiantService etudiantService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Etudiant>> getEtudiants() {
		return new ResponseEntity<List<Etudiant>>(etudiantService.getEtudiants(), HttpStatus.OK);
	}

	@PostMapping(value = "/etudiants", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant) {
		return new ResponseEntity<Etudiant>(etudiantService.createEtudiant(etudiant), HttpStatus.CREATED);
	}

	@PutMapping(value = "/etudiants/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Etudiant> updateEtudiant(@PathVariable("id") Long id, @RequestBody Etudiant etudiant) {
		return new ResponseEntity<Etudiant>(etudiantService.updateEtudiant(id, etudiant), HttpStatus.OK);
	}

	@GetMapping(value = "/etudiants/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Etudiant> getEtudiant(@PathVariable("id") Long id) {
		return new ResponseEntity<Etudiant>(etudiantService.getEtudiantById(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/etudiants/{id}")
	public ResponseEntity<?> deleteEtudiant(@PathVariable("id") Long id) {
		etudiantService.deleteEtudiant(id);
		return new ResponseEntity<>("Etudiant is deleted successfully", HttpStatus.OK);
	}

}
