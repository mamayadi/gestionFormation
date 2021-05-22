package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Etudiant;
import spring.jpa.model.Note;

public interface EtudiantService {
	public abstract Etudiant createEtudiant(Etudiant etudiant);

	public abstract List<Etudiant> getEtudiants();

	public abstract Etudiant getEtudiantById(Long id);

	public abstract Etudiant updateEtudiant(Long id, Etudiant etudiant);

	public abstract void deleteEtudiant(Long id);

	public abstract Etudiant assignerGroupeAuEtudiant(Long groupeId, Long etudiantId);

	public abstract Etudiant ajouterNoteAuEtudiant(Note note, Long etudiantId);

}
