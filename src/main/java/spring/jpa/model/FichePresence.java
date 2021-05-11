package spring.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class FichePresence {
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	@OneToOne
	private Etudiant etudiant;
	private boolean presence;

	public FichePresence(Etudiant etudiant, boolean presence) {
		super();
		this.etudiant = etudiant;
		this.presence = presence;
	}

	public FichePresence() {

	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public boolean isPresence() {
		return presence;
	}

	public void setPresence(boolean presence) {
		this.presence = presence;
	}

}
