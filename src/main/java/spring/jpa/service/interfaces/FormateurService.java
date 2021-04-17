package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.model.Seance;

public interface FormateurService {
	public void addSeancePourMatiere(Matiere matiere, Seance senace, Groupe groupe);

	public void affecterNoteDC(Matiere matiere, Etudiant etudiant, double noteDC);

	public void affecterNoteDS(Matiere matiere, Etudiant etudiant, double noteDS);

	public void affecterFichePresencePourSeance(Groupe groupe, Matiere matiere, Seance seance,
			List<FichePresence> listFichePresence);

	public void consulterListSeancePourGroupeMatiere(Groupe groupe, Matiere matiere);

}
