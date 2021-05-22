package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Formateur;

public interface FormateurService {
	public abstract Formateur createFormateur(Formateur formateur);

	public abstract List<Formateur> getFormateurs();

	public abstract Formateur getFormateurById(Long id);

	public abstract Formateur updateFormateur(Long id, Formateur formateur);

	public abstract void deleteFormateur(Long id);

	public abstract Formateur assignerGroupeAuFormateur(Long idFormateur, Long idGroupe);

	public abstract Formateur assignerMatiereAuFormateur(Long idFormateur, Long idMatiere);

}
