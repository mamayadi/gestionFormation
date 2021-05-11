package spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.model.Etudiant;
import spring.jpa.service.interfaces.EtudiantService;

@RestController
public class EtudiantServiceController {
	
	@Autowired
	EtudiantService etudiantService;
	
	@RequestMapping(value = "/etudiants", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getEtudiants() {
		return new ResponseEntity<>(etudiantService.getEtudiants(), HttpStatus.OK);
	}

	@RequestMapping(value = "/etudiants", method = RequestMethod.POST,produces="application/json")
	public ResponseEntity<Object> createEtudiant(@RequestBody Etudiant etudiant) {
		return new ResponseEntity<Object>(etudiantService.createEtudiant(etudiant), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/etudiants/{id}", method = RequestMethod.PUT,produces="application/json")
	public ResponseEntity<Object> updateEtudiant(@PathVariable("id") Long id, @RequestBody Etudiant etudiant) {
		return new ResponseEntity<Object>(etudiantService.updateEtudiant(id, etudiant), HttpStatus.OK);
	}

	@RequestMapping(value = "/etudiants/{id}", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getEtudiant(@PathVariable("id") Long id) {
		return new ResponseEntity<>(etudiantService.getEtudiantById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/etudiants/{id}", method = RequestMethod.DELETE,produces="application/json")
	public ResponseEntity<Object> deleteEtudiant(@PathVariable("id") Long id) {
		etudiantService.deleteEtudiant(id);
		return new ResponseEntity<>("Etudiant is deleted successsfully", HttpStatus.OK);
	}
	

}
