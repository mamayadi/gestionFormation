package spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.model.Seance;
import spring.jpa.service.interfaces.SeanceService;

@RestController
public class SeanceServiceController {

	@Autowired
	SeanceService seanceService;
	
	@RequestMapping(value = "/seances", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getSeances() {
		return new ResponseEntity<>(seanceService.getSeances(), HttpStatus.OK);
	}

	@RequestMapping(value = "/seances", method = RequestMethod.POST,produces="application/json")
	public ResponseEntity<Object> createSeance(@RequestBody Seance seance) {
		return new ResponseEntity<Object>(seanceService.createSeance(seance), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/seances/{id}", method = RequestMethod.PUT,produces="application/json")
	public ResponseEntity<Object> updateSeance(@PathVariable("id") Long id, @RequestBody Seance seance) {
		return new ResponseEntity<Object>(seanceService.updateSeance(id, seance), HttpStatus.OK);
	}

	@RequestMapping(value = "/seances/{id}", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getSeance(@PathVariable("id") Long id) {
		return new ResponseEntity<>(seanceService.getSeanceById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/seances/{id}", method = RequestMethod.DELETE,produces="application/json")
	public ResponseEntity<Object> deleteSeance(@PathVariable("id") Long id) {
		seanceService.deleteSeance(id);
		return new ResponseEntity<>("Seance is deleted successsfully", HttpStatus.OK);
	}
}
