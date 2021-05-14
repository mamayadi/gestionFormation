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

import spring.jpa.model.FichePresence;
import spring.jpa.service.interfaces.FichePresenceService;

@RestController
@RequestMapping(path = "/fichePresences")
public class FichePresenceServiceController {
	@Autowired
	FichePresenceService fichePresenceService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<FichePresence>> getFichePresences() {
		return new ResponseEntity<List<FichePresence>>(fichePresenceService.getFichePresences(), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<FichePresence> createFichePresence(@RequestBody FichePresence fichePresence) {
		return new ResponseEntity<FichePresence>(fichePresenceService.createFichePresence(fichePresence),
				HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<FichePresence> updateFichePresence(@PathVariable("id") Long id,
			@RequestBody FichePresence fichePresence) {
		return new ResponseEntity<FichePresence>(fichePresenceService.updateFichePresence(id, fichePresence),
				HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<FichePresence> getFichePresence(@PathVariable("id") Long id) {
		return new ResponseEntity<FichePresence>(fichePresenceService.getFichePresenceById(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteFichePresence(@PathVariable("id") Long id) {
		fichePresenceService.deleteFichePresence(id);
		return new ResponseEntity<>("Fiche Presence is deleted successfully", HttpStatus.OK);
	}

}
