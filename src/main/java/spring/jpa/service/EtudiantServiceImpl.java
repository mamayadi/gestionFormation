package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;
import spring.jpa.model.Seance;
import spring.jpa.repository.EtudiantRepository;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.service.interfaces.EtudiantService;

public class EtudiantServiceImpl implements EtudiantService {

	@Autowired
	private EtudiantRepository etudiantRepos;
	@Autowired
	private MatiereRepository matiereRepos;

	public EtudiantServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Note consulterNoteEtMoyenneParMatiere(Etudiant etudiant, Matiere matiere) {
		Etudiant foundedEtudiant = etudiantRepos.findById(etudiant.getId()).get();
		List<Note> listNote = foundedEtudiant.getListNote();
		Note foundedNote = null;
		for (Note note : listNote) {
			if (note.getMatiere().getId() == matiere.getId()) {
				foundedNote = note;
				break;
			}
		}
		return foundedNote;
	}

	@Override
	public List<FichePresence> consulterTauxPresenceParMatiere(Etudiant etudiant, Matiere matiere) {
		Matiere foundedMatiere = matiereRepos.findById(matiere.getId()).get();
		List<Seance> listSeance = foundedMatiere.getListSeance();
		List<FichePresence> foundedFicheList = null;
		for (Seance seance : listSeance) {
			List<FichePresence> listFiche = seance.getListFichePresence();
			for (FichePresence fichePresence : listFiche) {
				if (fichePresence.getEtudiant().getId() == etudiant.getId()) {
					foundedFicheList.add(fichePresence);
				}
			}
		}
		return foundedFicheList;

	}

}
