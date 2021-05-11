package spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.model.Note;
import spring.jpa.service.interfaces.NoteService;

@RestController
public class NoteServiceController {

	@Autowired
	NoteService noteService;
	
	@RequestMapping(value = "/notes", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getNotes() {
		return new ResponseEntity<>(noteService.getNotes(), HttpStatus.OK);
	}

	@RequestMapping(value = "/notes", method = RequestMethod.POST,produces="application/json")
	public ResponseEntity<Object> createNote(@RequestBody Note note) {
		return new ResponseEntity<Object>(noteService.createNote(note), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT,produces="application/json")
	public ResponseEntity<Object> updateNote(@PathVariable("id") Long id, @RequestBody Note note) {
		return new ResponseEntity<Object>(noteService.updateNote(id, note), HttpStatus.OK);
	}

	@RequestMapping(value = "/notes/{id}", method = RequestMethod.GET,produces="application/json")
	public ResponseEntity<Object> getNote(@PathVariable("id") Long id) {
		return new ResponseEntity<>(noteService.getNoteById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE,produces="application/json")
	public ResponseEntity<Object> deleteNote(@PathVariable("id") Long id) {
		noteService.deleteNote(id);
		return new ResponseEntity<>("Note is deleted successsfully", HttpStatus.OK);
	}
}
