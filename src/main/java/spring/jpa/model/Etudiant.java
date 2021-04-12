package spring.jpa.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("etudiant")
public class Etudiant extends Personne {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Groupe groupe;
	@OneToMany
	private List<Note> listNote;
	

	public Etudiant(String nom, String prenom, String username, String password) {
		super(nom, prenom, username, password, Role.ETUDIANT);
		// TODO Auto-generated constructor stub
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
}
