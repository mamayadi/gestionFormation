package spring.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import javax.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spring.jpa.model.Seance;
import spring.jpa.service.interfaces.SeanceService;

@RestController
@RequestMapping(path = "/seances")
public class SeanceServiceController {

	@Autowired
	SeanceService seanceService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Seance>> getSeances() {
		return new ResponseEntity<List<Seance>>(seanceService.getSeances(), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Seance> createSeance(@RequestBody Seance seance) {
		return new ResponseEntity<Seance>(seanceService.createSeance(seance), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Seance> updateSeance(@PathVariable("id") Long id, @RequestBody Seance seance) {
		return new ResponseEntity<Seance>(seanceService.updateSeance(id, seance), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Seance> getSeance(@PathVariable("id") Long id) {
		return new ResponseEntity<Seance>(seanceService.getSeanceById(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteSeance(@PathVariable("id") Long id) {
		seanceService.deleteSeance(id);
		return new ResponseEntity<>("Seance is deleted successfully", HttpStatus.OK);
	}
}
