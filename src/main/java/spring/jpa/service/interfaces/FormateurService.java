package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.model.Seance;

public interface FormateurService {
	public abstract Formateur createFormateur(Formateur formateur);

	public abstract List<Formateur> getFormateurs();

	public abstract Formateur getFormateurById(Long id);

	public abstract Formateur updateFormateur(Long id, Formateur formateur);

	public abstract void deleteFormateur(Long id);

	public abstract void addSeancePourMatiere(Matiere matiere, Seance senace);

	public abstract void affecterNoteDC(Matiere matiere, Etudiant etudiant, double noteDC);

	public abstract void affecterNoteDS(Matiere matiere, Etudiant etudiant, double noteDS);

	public abstract void affecterFichePresencePourSeance(Groupe groupe, Matiere matiere, Seance seance,
			List<FichePresence> listFichePresence);

	public abstract void consulterListSeancePourGroupeMatiere(Groupe groupe, Matiere matiere);

}
