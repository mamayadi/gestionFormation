package spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.model.FichePresence;
import spring.jpa.service.interfaces.FichePresenceService;

@RestController
public class FichePresenceServiceController {
	@Autowired
	FichePresenceService fichePresenceService;
	
	@RequestMapping(value = "/fichePresences", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getFichePresences() {
		return new ResponseEntity<>(fichePresenceService.getFichePresences(), HttpStatus.OK);
	}

	@RequestMapping(value = "/fichePresences", method = RequestMethod.POST,produces="application/json")
	public ResponseEntity<Object> createFichePresence(@RequestBody FichePresence fichePresence) {
		return new ResponseEntity<Object>(fichePresenceService.createFichePresence(fichePresence), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/fichePresences/{id}", method = RequestMethod.PUT,produces="application/json")
	public ResponseEntity<Object> updateFichePresence(@PathVariable("id") Long id, @RequestBody FichePresence fichePresence) {
		return new ResponseEntity<Object>(fichePresenceService.updateFichePresence(id, fichePresence), HttpStatus.OK);
	}

	@RequestMapping(value = "/fichePresences/{id}", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getFichePresence(@PathVariable("id") Long id) {
		return new ResponseEntity<>(fichePresenceService.getFichePresenceById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fichePresences/{id}", method = RequestMethod.DELETE,produces="application/json")
	public ResponseEntity<Object> deleteFichePresence(@PathVariable("id") Long id) {
		fichePresenceService.deleteFichePresence(id);
		return new ResponseEntity<>("Fiche Presence is deleted successsfully", HttpStatus.OK);
	}

}
