package spring.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Note {
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	private Double noteDC;
	private Double noteDS;
	private Double moyenne;
	@OneToOne
	private Matiere matiere;
	@OneToOne
	@JsonIgnoreProperties(value = { "groupe", "listNote" })
	private Etudiant etudiant;

	public Note(Double noteDC, Double noteDS) {
		this.noteDC = noteDC;
		this.noteDS = noteDS;
		this.moyenne = calculMoyenne();
	}

	public Note() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public Double calculMoyenne() {
		return this.noteDC * 0.4 + this.noteDS * 0.6;
	}

	public Double getNoteDC() {
		return noteDC;
	}

	public void setNoteDC(Double noteDC) {
		this.noteDC = noteDC;
		this.moyenne = calculMoyenne();
	}

	public Double getNoteDS() {
		return noteDS;
	}

	public void setNoteDS(Double noteDS) {
		this.noteDS = noteDS;
		this.moyenne = calculMoyenne();
	}

	public Double getMoyenne() {
		return moyenne;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

}
