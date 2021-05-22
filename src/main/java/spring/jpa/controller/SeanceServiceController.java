package spring.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import javax.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import spring.jpa.enums.DeleteResponse;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Seance;
import spring.jpa.service.interfaces.SeanceService;

@RestController
@RequestMapping(path = "/seances")
@SecurityRequirement(name = "JwtAuthentication")
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
		return new ResponseEntity<>(new DeleteResponse("Seance is deleted successfully"), HttpStatus.OK);
	}

	/***************************/
	@PostMapping(value = "/{idSeance}/add-fiche-presence", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Seance> addFichePresence(@PathVariable("idSeance") Long idSeance,
			@RequestBody FichePresence fichePresence) {
		return new ResponseEntity<Seance>(seanceService.ajoutFichePresenceAuSeance(idSeance, fichePresence),
				HttpStatus.CREATED);
	}

	@PostMapping(value = "/{idSeance}/add-list-fiche-presence", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Seance> addListFichePresence(@PathVariable("idSeance") Long idSeance,
			@RequestBody List<FichePresence> listFichePresence) {
		return new ResponseEntity<Seance>(seanceService.affecterFichePresencePourSeance(idSeance, listFichePresence),
				HttpStatus.CREATED);
	}

	@GetMapping(value = "/{idMatiere}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Seance>> getSeancesParMatiere(@PathVariable("idMatiere") Long idMatiere) {
		return new ResponseEntity<List<Seance>>(seanceService.consulterListSeancePourMatiere(idMatiere), HttpStatus.OK);
	}
}
