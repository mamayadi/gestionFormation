package spring.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Note {
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	private double noteDC;
	private double noteDS;
	private double moyenne;
	@OneToOne
	private Matiere matiere;
	@OneToOne
	private Etudiant etudiant;

	public Note(double noteDC, double noteDS) {
		this.noteDC = noteDC;
		this.noteDS = noteDS;
		this.moyenne = calculMoyenne();
	}

	public Note() {
		// TODO Auto-generated constructor stub
	}

	public double calculMoyenne() {
		return this.noteDC * 0.4 + this.noteDS * 0.6;
	}

	public double getNoteDC() {
		return noteDC;
	}

	public void setNoteDC(double noteDC) {
		this.noteDC = noteDC;
		this.moyenne = calculMoyenne();
	}

	public double getNoteDS() {
		return noteDS;
	}

	public void setNoteDS(double noteDS) {
		this.noteDS = noteDS;
		this.moyenne = calculMoyenne();
	}

	public double getMoyenne() {
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
