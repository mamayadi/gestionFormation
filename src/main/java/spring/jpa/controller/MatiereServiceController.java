package spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.model.Matiere;
import spring.jpa.service.interfaces.MatiereService;

@RestController
public class MatiereServiceController {

	@Autowired
	MatiereService matiereService;
	
	@RequestMapping(value = "/matieres", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getMatieres() {
		return new ResponseEntity<>(matiereService.getMatieres(), HttpStatus.OK);
	}

	@RequestMapping(value = "/matieres", method = RequestMethod.POST,produces="application/json")
	public ResponseEntity<Object> createMatiere(@RequestBody Matiere matiere) {
		return new ResponseEntity<Object>(matiereService.createMatiere(matiere), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/matieres/{id}", method = RequestMethod.PUT,produces="application/json")
	public ResponseEntity<Object> updateMatiere(@PathVariable("id") Long id, @RequestBody Matiere matiere) {
		return new ResponseEntity<Object>(matiereService.updateMatiere(id, matiere), HttpStatus.OK);
	}

	@RequestMapping(value = "/matieres/{id}", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getMatiere(@PathVariable("id") Long id) {
		return new ResponseEntity<>(matiereService.getMatiereById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/matieres/{id}", method = RequestMethod.DELETE,produces="application/json")
	public ResponseEntity<Object> deleteMatiere(@PathVariable("id") Long id) {
		matiereService.deleteMatiere(id);
		return new ResponseEntity<>("Matiere is deleted successsfully", HttpStatus.OK);
	}
}
