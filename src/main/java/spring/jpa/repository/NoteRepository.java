package spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.jpa.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
