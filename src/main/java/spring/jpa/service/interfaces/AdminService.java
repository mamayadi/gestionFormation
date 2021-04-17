package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Admin;
import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;

public interface AdminService {
	public void createFormateur(Formateur formateur);

	public void createAdmin(Admin admin);

	public void createEtudiant(Etudiant etudiant);

	public void assignerGroupeAuFormateur(Groupe groupe, Formateur formateur);

	public void assignerMatiereAuFormateur(Matiere matiere, Formateur formateur);

	public void createMatiere(Matiere matiere);

	public void createGroupe(Groupe groupe);

	public double determinerMoyenneEtudiantParMatiere(Etudiant etudiant, Matiere matiere);

	public void consulterHistoriquePresenceParGroupe(Groupe groupe);

	public List<FichePresence> consulterHistoriquePresenceParEtudiant(Etudiant etudiant);

}
