package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;

public interface EtudiantService {
	public Note consulterNoteEtMoyenneParMatiere(Etudiant etudiant, Matiere matiere);

	public List<FichePresence> consulterTauxPresenceParMatiere(Etudiant etudiant, Matiere matiere);
}
