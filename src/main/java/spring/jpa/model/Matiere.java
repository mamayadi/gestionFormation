package spring.jpa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Matiere {
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	private double volumeHoraire;
	private String libelle;
	private double nombreHeureEnseigne;
	@OneToMany
	private List<Seance> listSeance;
	@OneToOne
	private Groupe groupe;

	public Matiere(double volumeHoraire, String libelle) {
		super();
		this.volumeHoraire = volumeHoraire;
		this.libelle = libelle;
		this.nombreHeureEnseigne = 0;
	}

	public Matiere() {

	}

	public double getVolumeHoraire() {
		return volumeHoraire;
	}

	public void setVolumeHoraire(double volumeHoraire) {
		this.volumeHoraire = volumeHoraire;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getNombreHeureEnseigne() {
		return nombreHeureEnseigne;
	}

	public void setNombreHeureEnseigne(double nombreHeureEnseigne) {
		if (this.volumeHoraire > nombreHeureEnseigne) {
			this.nombreHeureEnseigne = nombreHeureEnseigne;
		}
	}

	public void addNombreHeureEnseigne(double nombreHeure) {
		double totalNombreHeure = this.nombreHeureEnseigne + nombreHeure;
		setNombreHeureEnseigne(totalNombreHeure);
	}

	public List<Seance> getListSeance() {
		return listSeance;
	}

	public void setListSeance(List<Seance> listSeance) {
		this.listSeance = listSeance;
	}

	public void addSeance(Seance seance) {
		this.listSeance.add(seance);
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Long getId() {
		return id;
	}

}
