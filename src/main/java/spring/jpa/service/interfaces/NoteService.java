package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Note;

public interface NoteService {
	public abstract Note createNote(Note note);

	public abstract List<Note> getNotes();

	public abstract Note getNoteById(Long id);

	public abstract Note updateNote(Long id, Note note);

	public abstract void deleteNote(Long id);
}
