package spring.jpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Seance {
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date date;
	private Double duree;
	private String description;
	@OneToMany
	private List<FichePresence> listFichePresence;

	public Seance(Date date, Double duree, String description) {
		super();
		this.date = date;
		this.duree = duree;
		this.description = description;
	}

	public Seance() {

	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public Double getDuree() {
		return duree;
	}

	public void setDuree(Double duree) {
		this.duree = duree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FichePresence> getListFichePresence() {
		return listFichePresence;
	}

	public void setListFichePresence(List<FichePresence> listFichePresence) {
		this.listFichePresence = listFichePresence;
	}

	public void addFichePresence(FichePresence fichePresence) {
		this.listFichePresence.add(fichePresence);
	}
}
