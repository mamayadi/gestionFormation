package spring.jpa.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import spring.jpa.enums.Role;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE_PERSONNE")
@DiscriminatorValue("Personne")
public class Personne {
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	private String nom;
	private String prenom;
	private String username;
	@JsonIgnore
	private String password;
	private Role role;

	public Personne(String nom, String prenom, String username, String password, Role role) {
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Personne() {

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public boolean isAdmin() {
		return this.role == Role.ADMIN;
	}
	
	public boolean isFormateur() {
		return this.role == Role.FORMATEUR;
	}
	
	public boolean isEtudiant() {
		return this.role == Role.ETUDIANT;
	}

}
