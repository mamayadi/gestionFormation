package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;

public interface EtudiantService {
	public abstract Etudiant createEtudiant(Etudiant etudiant);

	public abstract List<Etudiant> getEtudiants();

	public abstract Etudiant getEtudiantById(Long id);

	public abstract Etudiant updateEtudiant(Long id, Etudiant etudiant);

	public abstract void deleteEtudiant(Long id);

	public abstract Note consulterNoteEtMoyenneParMatiere(Etudiant etudiant, Matiere matiere);

	public abstract List<FichePresence> consulterTauxPresenceParMatiere(Etudiant etudiant, Matiere matiere);
}
