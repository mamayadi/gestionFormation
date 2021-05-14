package spring.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import spring.jpa.enums.Role;

@Entity
@DiscriminatorValue("etudiant")
public class Etudiant extends Personne {
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	@OneToOne
	@JsonIgnoreProperties(value = {"listEtudiant"})
	private Groupe groupe;
	@OneToMany
	@JsonIgnoreProperties(value = {"etudiant"})
	private List<Note> listNote;

	public Etudiant(String nom, String prenom, String username, String password) {
		super(nom, prenom, username, password, Role.ETUDIANT);
		this.listNote = new ArrayList<Note>();
	}

	public Etudiant() {
		// TODO Auto-generated constructor stub
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public List<Note> getListNote() {
		return listNote;
	}

	public void setListNote(List<Note> listNote) {
		this.listNote = listNote;
	}
	
	public void addNote(Note note) {
		this.listNote.add(note);
	}

	public Long getId() {
		return id;
	}

}
