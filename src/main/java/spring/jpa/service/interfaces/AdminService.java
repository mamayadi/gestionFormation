package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Admin;
import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;
import spring.jpa.model.Seance;

public interface AdminService {

	public abstract Admin createAdmin(Admin admin);

	public abstract List<Admin> getAdmins();

	public abstract Admin updateAdmin(Long id, Admin admin);

	public abstract Admin getAdminById(Long id);

	public abstract void deleteAdmin(Long id);

	public abstract Formateur assignerGroupeAuFormateur(long groupeId, long formateurId);

	public abstract Formateur assignerMatiereAuFormateur(long matiereId, long formateurId);

	public abstract Etudiant assignerGroupeAuEtudiant(long groupeId, long etudiantId);

	public abstract Groupe assignerMatiereAuGroupe(long matiereId, long groupeId);

	public abstract Matiere ajoutSeancePourMatiere(Seance seance, long matiereId);

	public abstract Etudiant ajouterNoteAuEtudiant(Note note, long etudiantId);

	public abstract Seance ajoutFichePresenceAuSeance(FichePresence fichePresence, long seanceId);

	public abstract double determinerMoyenneEtudiantParMatiere(long etudiantId, long matiereId);

	public abstract List<FichePresence> consulterHistoriquePresenceParGroupe(long groupeId);

	public abstract List<FichePresence> consulterHistoriquePresenceParEtudiant(long etudiantId);

}
