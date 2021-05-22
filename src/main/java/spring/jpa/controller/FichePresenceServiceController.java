package spring.jpa.controller;

import java.util.Collections;
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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import spring.jpa.enums.DeleteResponse;
import spring.jpa.model.FichePresence;
import spring.jpa.service.interfaces.FichePresenceService;

@RestController
@RequestMapping(path = "/fichePresences")
@SecurityRequirement(name = "JwtAuthentication")
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
		return new ResponseEntity<>(new DeleteResponse("Fiche Presence is deleted successfully"), HttpStatus.OK);
	}

	/*****************/
	@GetMapping(value = "/{idEtudiant}/taux-presence-matiere/{idMatiere}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getTauxPresenceMatiere(@PathVariable("idEtudiant") Long idEtudiant,
			@PathVariable("idMatiere") Long idMatiere) {
		return new ResponseEntity<>(Collections.singletonMap("tauxPresence",
				fichePresenceService.consulterTauxPresenceParMatiere(idEtudiant, idMatiere)), HttpStatus.OK);
	}

	@GetMapping(value = "/historique-presence-groupe/{idGroupe}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<FichePresence>> getHistoriquePresenceParGroupe(@PathVariable("idGroupe") Long idGroupe) {
		return new ResponseEntity<List<FichePresence>>(
				fichePresenceService.consulterHistoriquePresenceParGroupe(idGroupe), HttpStatus.OK);
	}

	@GetMapping(value = "/historique-presence-etudiant/{idEtudiant}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<FichePresence>> getHistoriquePresenceParEtudiant(
			@PathVariable("idEtudiant") Long idEtudiant) {
		return new ResponseEntity<List<FichePresence>>(
				fichePresenceService.consulterHistoriquePresenceParEtudiant(idEtudiant), HttpStatus.OK);
	}

}
