package spring.jpa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Groupe {
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	private String libelle;
	@OneToMany
	private List<Etudiant> listEtudiant;
	@ManyToMany
	private List<Matiere> listMatiere;

	public Groupe() {
		// TODO Auto-generated constructor stub
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Etudiant> getListEtudiant() {
		return listEtudiant;
	}

	public void setListEtudiant(List<Etudiant> listEtudiant) {
		this.listEtudiant = listEtudiant;
	}

	public void addEtudiant(Etudiant etudiant) {
		this.listEtudiant.add(etudiant);
	}

	public List<Matiere> getListMatiere() {
		return listMatiere;
	}

	public void setListMatiere(List<Matiere> listMatiere) {
		this.listMatiere = listMatiere;
	}

	public void addMatiere(Matiere matiere) {
		this.listMatiere.add(matiere);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
