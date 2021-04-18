package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.model.Seance;
import spring.jpa.repository.FormateurRepository;
import spring.jpa.repository.GroupeRepository;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.repository.SeanceRepository;
import spring.jpa.service.interfaces.FormateurService;

public class FormateurServiceImpl implements FormateurService {
	@Autowired
	private FormateurRepository formateurRepos;
	@Autowired
	private SeanceRepository seanceRepos;
	@Autowired
	private MatiereRepository matiereRepos;
	@Autowired
	private GroupeRepository groupeRepos;

	@Override
	public Formateur createFormateur(Formateur formateur) {
		return formateurRepos.save(formateur);
	}

	@Override
	public List<Formateur> getFormateurs() {
		return formateurRepos.findAll();
	}

	@Override
	public Formateur getFormateur(Long id) {
		return formateurRepos.findById(id).get();
	}

	@Override
	public Formateur updateFormateur(Long id, Formateur formateur) {
		Formateur foundedFormateur = formateurRepos.findById(id).get();
		foundedFormateur.setNom(formateur.getNom());
		foundedFormateur.setPrenom(formateur.getPrenom());
		return formateurRepos.save(foundedFormateur);
	}

	@Override
	public void deleteFormateur(Long id) {
		formateurRepos.deleteById(id);

	}

	@Override
	public void addSeancePourMatiere(Matiere matiere, Seance senace) {
		Seance createdSeance = seanceRepos.save(senace);
		matiere.addSeance(createdSeance);
		matiereRepos.save(matiere);
	}

	@Override
	public void affecterNoteDC(Matiere matiere, Etudiant etudiant, double noteDC) {
		// TODO Auto-generated method stub

	}

	@Override
	public void affecterNoteDS(Matiere matiere, Etudiant etudiant, double noteDS) {
		// TODO Auto-generated method stub

	}

	@Override
	public void affecterFichePresencePourSeance(Groupe groupe, Matiere matiere, Seance seance,
			List<FichePresence> listFichePresence) {
		// TODO Auto-generated method stub

	}

	@Override
	public void consulterListSeancePourGroupeMatiere(Groupe groupe, Matiere matiere) {
		// TODO Auto-generated method stub

	}

}
