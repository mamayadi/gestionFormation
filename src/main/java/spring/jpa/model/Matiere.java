package spring.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Matiere {
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	private Double volumeHoraire;
	private String libelle;
	private Double nombreHeureEnseigne;
	@OneToMany
	private List<Seance> listSeance;
	@OneToOne
	@JsonIgnoreProperties(value = { "listMatiere" })
	private Groupe groupe;

	public Matiere(double volumeHoraire, String libelle) {
		this.volumeHoraire = volumeHoraire;
		this.libelle = libelle;
		this.nombreHeureEnseigne = 0.0;
		this.listSeance = new ArrayList<Seance>();
	}

	public Matiere() {

	}

	public Double getVolumeHoraire() {
		return volumeHoraire;
	}

	public void setVolumeHoraire(Double volumeHoraire) {
		this.volumeHoraire = volumeHoraire;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Double getNombreHeureEnseigne() {
		return nombreHeureEnseigne;
	}

	public void setNombreHeureEnseigne(Double nombreHeureEnseigne) {
		if (this.volumeHoraire > nombreHeureEnseigne) {
			this.nombreHeureEnseigne = nombreHeureEnseigne;
		}
	}

	public void addNombreHeureEnseigne(Double nombreHeure) {
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
