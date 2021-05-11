package spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.model.Formateur;
import spring.jpa.service.interfaces.FormateurService;

@RestController
public class FormateurServiceController {
	@Autowired
	FormateurService formateurService;
	
	@RequestMapping(value = "/formateurs", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getFormateurs() {
		return new ResponseEntity<>(formateurService.getFormateurs(), HttpStatus.OK);
	}

	@RequestMapping(value = "/formateurs", method = RequestMethod.POST,produces="application/json")
	public ResponseEntity<Object> createFormateur(@RequestBody Formateur formateur) {
		return new ResponseEntity<Object>(formateurService.createFormateur(formateur), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/formateurs/{id}", method = RequestMethod.PUT,produces="application/json")
	public ResponseEntity<Object> updateFormateur(@PathVariable("id") Long id, @RequestBody Formateur formateur) {
		return new ResponseEntity<Object>(formateurService.updateFormateur(id, formateur), HttpStatus.OK);
	}

	@RequestMapping(value = "/formateurs/{id}", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getFormateur(@PathVariable("id") Long id) {
		return new ResponseEntity<>(formateurService.getFormateurById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/formateurs/{id}", method = RequestMethod.DELETE,produces="application/json")
	public ResponseEntity<Object> deleteFormateur(@PathVariable("id") Long id) {
		formateurService.deleteFormateur(id);
		return new ResponseEntity<>("Formateur is deleted successsfully", HttpStatus.OK);
	}
}
