package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Admin;
import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;

public interface AdminService {
	public abstract void createFormateur(Formateur formateur);

	public abstract Admin createAdmin(Admin admin);

	public abstract List<Admin> getAdmins();

	public abstract Admin updateAdmin(Long id,Admin admin);

	public abstract Admin getAdminById(Long id);

	public abstract void deleteAdmin(Long id);

	public abstract void createEtudiant(Etudiant etudiant);

	public abstract void assignerGroupeAuFormateur(Groupe groupe, Formateur formateur);

	public abstract void assignerMatiereAuFormateur(Matiere matiere, Formateur formateur);

	public abstract void createMatiere(Matiere matiere);

	public abstract void createGroupe(Groupe groupe);

	public abstract double determinerMoyenneEtudiantParMatiere(Etudiant etudiant, Matiere matiere);

	public abstract void consulterHistoriquePresenceParGroupe(Groupe groupe);

	public abstract List<FichePresence> consulterHistoriquePresenceParEtudiant(Etudiant etudiant);

}
