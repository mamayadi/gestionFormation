package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;
import spring.jpa.model.Seance;

public interface FormateurService {
	public abstract Formateur createFormateur(Formateur formateur);

	public abstract List<Formateur> getFormateurs();

	public abstract Formateur getFormateurById(Long id);

	public abstract Formateur updateFormateur(Long id, Formateur formateur);

	public abstract void deleteFormateur(Long id);

	public abstract Matiere addSeancePourMatiere(Matiere matiere, Seance senace);

	public abstract Note addNote(Matiere matiere, Etudiant etudiant, Note note);
	
	public abstract Note affecterNoteDC(Matiere matiere, Etudiant etudiant, double noteDC);

	public abstract Note affecterNoteDS(Matiere matiere, Etudiant etudiant, double noteDS);

	public abstract Seance affecterFichePresencePourSeance(Seance seance,
			List<FichePresence> listFichePresence);

	public abstract List<Seance> consulterListSeancePourGroupeMatiere( Matiere matiere);

}
