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

import spring.jpa.model.Formateur;
import spring.jpa.service.interfaces.FormateurService;

@RestController
@RequestMapping(path = "/formateurs")
public class FormateurServiceController {
	@Autowired
	FormateurService formateurService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Formateur>> getFormateurs() {
		return new ResponseEntity<List<Formateur>>(formateurService.getFormateurs(), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Formateur> createFormateur(@RequestBody Formateur formateur) {
		return new ResponseEntity<Formateur>(formateurService.createFormateur(formateur), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Formateur> updateFormateur(@PathVariable("id") Long id, @RequestBody Formateur formateur) {
		return new ResponseEntity<Formateur>(formateurService.updateFormateur(id, formateur), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Formateur> getFormateur(@PathVariable("id") Long id) {
		return new ResponseEntity<Formateur>(formateurService.getFormateurById(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteFormateur(@PathVariable("id") Long id) {
		formateurService.deleteFormateur(id);
		return new ResponseEntity<>("Formateur is deleted successfully", HttpStatus.OK);
	}
}
