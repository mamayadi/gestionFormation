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

import spring.jpa.model.Matiere;
import spring.jpa.service.interfaces.MatiereService;

@RestController
@RequestMapping(path = "/matieres")
public class MatiereServiceController {

	@Autowired
	MatiereService matiereService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Matiere>> getMatieres() {
		return new ResponseEntity<List<Matiere>>(matiereService.getMatieres(), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Matiere> createMatiere(@RequestBody Matiere matiere) {
		return new ResponseEntity<Matiere>(matiereService.createMatiere(matiere), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Matiere> updateMatiere(@PathVariable("id") Long id, @RequestBody Matiere matiere) {
		return new ResponseEntity<Matiere>(matiereService.updateMatiere(id, matiere), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Matiere> getMatiere(@PathVariable("id") Long id) {
		return new ResponseEntity<Matiere>(matiereService.getMatiereById(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteMatiere(@PathVariable("id") Long id) {
		matiereService.deleteMatiere(id);
		return new ResponseEntity<>("Matiere is deleted successfully", HttpStatus.OK);
	}
}
