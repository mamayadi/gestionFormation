package spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.model.Groupe;
import spring.jpa.service.interfaces.GroupeService;

@RestController
public class GroupeServiceController {

	@Autowired
	GroupeService groupeService;
	
	@RequestMapping(value = "/groupes", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getGroupes() {
		return new ResponseEntity<>(groupeService.getGroupes(), HttpStatus.OK);
	}

	@RequestMapping(value = "/groupes", method = RequestMethod.POST,produces="application/json")
	public ResponseEntity<Object> createGroupe(@RequestBody Groupe groupe) {
		return new ResponseEntity<Object>(groupeService.createGroupe(groupe), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/groupes/{id}", method = RequestMethod.PUT,produces="application/json")
	public ResponseEntity<Object> updateGroupe(@PathVariable("id") Long id, @RequestBody Groupe groupe) {
		return new ResponseEntity<Object>(groupeService.updateGroupe(id, groupe), HttpStatus.OK);
	}

	@RequestMapping(value = "/groupes/{id}", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getGroupe(@PathVariable("id") Long id) {
		return new ResponseEntity<>(groupeService.getGroupeById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/groupes/{id}", method = RequestMethod.DELETE,produces="application/json")
	public ResponseEntity<Object> deleteGroupe(@PathVariable("id") Long id) {
		groupeService.deleteGroupe(id);
		return new ResponseEntity<>("Groupe is deleted successsfully", HttpStatus.OK);
	}
}
