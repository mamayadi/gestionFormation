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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import spring.jpa.model.Groupe;
import spring.jpa.service.interfaces.GroupeService;

@RestController
@RequestMapping(path = "/groupes")
@SecurityRequirement(name = "JwtAuthentication")
public class GroupeServiceController {

	@Autowired
	GroupeService groupeService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Groupe>> getGroupes() {
		return new ResponseEntity<List<Groupe>>(groupeService.getGroupes(), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Groupe> createGroupe(@RequestBody Groupe groupe) {
		return new ResponseEntity<Groupe>(groupeService.createGroupe(groupe), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Groupe> updateGroupe(@PathVariable("id") Long id, @RequestBody Groupe groupe) {
		return new ResponseEntity<Groupe>(groupeService.updateGroupe(id, groupe), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Groupe> getGroupe(@PathVariable("id") Long id) {
		return new ResponseEntity<Groupe>(groupeService.getGroupeById(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteGroupe(@PathVariable("id") Long id) {
		groupeService.deleteGroupe(id);
		return new ResponseEntity<>("Groupe is deleted successfully", HttpStatus.OK);
	}
}
