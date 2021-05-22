package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Matiere;
import spring.jpa.model.Seance;

public interface MatiereService {
	public abstract Matiere createMatiere(Matiere matiere);

	public abstract List<Matiere> getMatieres();

	public abstract Matiere getMatiereById(Long id);

	public abstract Matiere updateMatiere(Long id, Matiere matiere);

	public abstract void deleteMatiere(Long id);

	public abstract Matiere ajoutSeancePourMatiere(Long idMatiere, Seance seance);

	public abstract List<Matiere> getListMatiereParGroupe(Long idGroupe);

}
