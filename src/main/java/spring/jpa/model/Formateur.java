package spring.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import spring.jpa.enums.Role;

@Entity
@DiscriminatorValue("formateur")
public class Formateur extends Personne {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	@OneToMany
	private List<Matiere> listMatiere;
	@ManyToMany
	@JsonIgnoreProperties(value = { "listMatiere"})
	private List<Groupe> listGroupe;

	public Formateur(String nom, String prenom, String username, String password) {
		super(nom, prenom, username, password, Role.FORMATEUR);
		this.listGroupe = new ArrayList<Groupe>();
		this.listMatiere = new ArrayList<Matiere>();
	}

	public Formateur() {
		// TODO Auto-generated constructor stub
	}

	public List<Matiere> getListMatiere() {
		return listMatiere;
	}

	public void setListMatiere(List<Matiere> listMatiere) {
		this.listMatiere = listMatiere;
	}

	public List<Groupe> getListGroupe() {
		return listGroupe;
	}

	public void setListGroupe(List<Groupe> listGroupe) {
		this.listGroupe = listGroupe;
	}

	public void addGroupe(Groupe groupe) {
		this.listGroupe.add(groupe);
	}

	public void addMatiere(Matiere matiere) {
		this.listMatiere.add(matiere);
	}

	public Long getId() {
		return id;
	}

}
