package spring.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.BadRequestException;
import spring.jpa.exceptions.NotFoundException;
import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;
import spring.jpa.model.Seance;
import spring.jpa.repository.EtudiantRepository;
import spring.jpa.repository.FichePresenceRepository;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.repository.NoteRepository;
import spring.jpa.repository.SeanceRepository;
import spring.jpa.service.interfaces.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired
	private SeanceRepository seanceRepos;
	@Autowired
	private MatiereRepository matiereRepos;
	@Autowired
	private FichePresenceRepository fichePresenceRepos;
	@Autowired
	private EtudiantRepository etudiantRepos;
	@Autowired
	private NoteRepository noteRepos;

	public SeanceServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public Seance createSeance(Seance seance) {
		Matiere foundedMatiere = matiereRepos.findById(seance.getMatiere().getId())
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		Groupe groupe = foundedMatiere.getGroupe();
		List<FichePresence> listFichePresence = new ArrayList<FichePresence>();
		for (Etudiant etudiant : groupe.getListEtudiant()) {
			FichePresence fichePresence = fichePresenceRepos.save(new FichePresence(etudiant, false));
			listFichePresence.add(fichePresence);
		}
		seance.setListFichePresence(listFichePresence);
		Seance createdSeance = seanceRepos.save(seance);
		foundedMatiere.setNombreHeureEnseigne(foundedMatiere.getNombreHeureEnseigne() + createdSeance.getDuree());
		foundedMatiere.addSeance(createdSeance);
		matiereRepos.save(foundedMatiere);
		return createdSeance;
	}

	public List<Seance> getSeances() {
		return seanceRepos.findAll();
	}

	public Seance getSeanceById(Long id) {
		return seanceRepos.findById(id).orElseThrow(() -> new NotFoundException("Seance not found!"));
	}

	public Seance updateSeance(Long id, Seance seance) throws BadRequestException {
		Seance foundedSeance = getSeanceById(id);
		Matiere matiere = matiereRepos.findById(foundedSeance.getMatiere().getId())
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		if (seance.getDate() != null) {
			foundedSeance.setDate(seance.getDate());
		}
		if (seance.getDescription() != null) {
			foundedSeance.setDescription(seance.getDescription());
		}
		if (seance.getDuree() != null) {
			foundedSeance.setDuree(seance.getDuree());
			Double totalNmbreHeure = matiere.getNombreHeureEnseigne() + foundedSeance.getDuree();
			if (totalNmbreHeure < matiere.getVolumeHoraire()) {
				matiere.setNombreHeureEnseigne(totalNmbreHeure);
				matiereRepos.save(matiere);
			} else {
				throw new BadRequestException("Durée de la séance supérieure au volume horaire autorisé du matière !");
			}
		}
		if (seance.getListFichePresence() != null) {
			List<FichePresence> updatedListFiche = new ArrayList<FichePresence>();
			for (FichePresence fichePresence : seance.getListFichePresence()) {
				FichePresence savedFiche = fichePresenceRepos.save(fichePresence);
				updatedListFiche.add(savedFiche);
				if(!(consulterTauxPresenceParMatiere(savedFiche.getEtudiant().getId(), matiere.getId())>20)) {
					Etudiant etudiant = savedFiche.getEtudiant();
					Note foundedNote = null;
					for (Note note : etudiant.getListNote()) {
						if(note.getMatiere().getId() == matiere.getId()) {
							foundedNote = note;
						}
					}
					Note savedNote= null;
					if(foundedNote != null) {
						foundedNote.setNoteDC(0.0);
						foundedNote.setNoteDS(0.0);
						savedNote= noteRepos.save(foundedNote);
					} else {
						 savedNote= noteRepos.save(new Note(0.0,0.0));
					}
					etudiant.addNote(savedNote);
					etudiantRepos.save(etudiant);
				}
			}
			foundedSeance.setListFichePresence(updatedListFiche);
		}
		return seanceRepos.save(foundedSeance);
	}

	public void deleteSeance(Long id) {
		Seance seance = getSeanceById(id);
		seanceRepos.delete(seance);
	}
	
	/******* End CRUD Seance ******/
	public double consulterTauxPresenceParMatiere(Long id, Long matiereId) {
		Etudiant etudiant = etudiantRepos.findById(id)
				.orElseThrow(() -> new NotFoundException("Etudiant not found!"));
		Matiere foundedMatiere = matiereRepos.findById(matiereId)
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		List<Seance> listSeance = foundedMatiere.getListSeance();
		double total = 0;
		for (Seance seance : listSeance) {
			List<FichePresence> listFiche = seance.getListFichePresence();
			for (FichePresence fichePresence : listFiche) {
				if (fichePresence.isPresence() && fichePresence.getEtudiant().getId() == etudiant.getId()) {
					total+=1;
				}
			}
		}
		return total *100/foundedMatiere.getListSeance().size();
	}
}
