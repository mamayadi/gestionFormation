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

import spring.jpa.model.Note;
import spring.jpa.service.interfaces.NoteService;

@RestController
@RequestMapping(path = "/notes")
public class NoteServiceController {

	@Autowired
	NoteService noteService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Note>> getNotes() {
		return new ResponseEntity<List<Note>>(noteService.getNotes(), HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Note> createNote(@RequestBody Note note) {
		return new ResponseEntity<Note>(noteService.createNote(note), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note note) {
		return new ResponseEntity<Note>(noteService.updateNote(id, note), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Note> getNote(@PathVariable("id") Long id) {
		return new ResponseEntity<Note>(noteService.getNoteById(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable("id") Long id) {
		noteService.deleteNote(id);
		return new ResponseEntity<>("Note is deleted successfully", HttpStatus.OK);
	}
}
