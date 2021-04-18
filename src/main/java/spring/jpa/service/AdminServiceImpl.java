package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.model.Admin;
import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;
import spring.jpa.model.Formateur;
import spring.jpa.model.Groupe;
import spring.jpa.model.Matiere;
import spring.jpa.model.Note;
import spring.jpa.model.Seance;
import spring.jpa.repository.AdminRepository;
import spring.jpa.repository.EtudiantRepository;
import spring.jpa.repository.FichePresenceRepository;
import spring.jpa.repository.FormateurRepository;
import spring.jpa.repository.GroupeRepository;
import spring.jpa.repository.MatiereRepository;
import spring.jpa.service.interfaces.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepos;
	@Autowired
	private FormateurRepository formateurRepos;
	@Autowired
	private EtudiantRepository etudiantRepos;
	@Autowired
	private MatiereRepository matiereRepos;
	@Autowired
	private GroupeRepository groupeRepos;
	@Autowired
	private FichePresenceRepository fichePresenceRepos;

	@Override
	public void createFormateur(Formateur formateur) {
		formateurRepos.save(formateur);

	}

	@Override
	public Admin createAdmin(Admin admin) {
		return adminRepos.save(admin);
	}

	@Override
	public List<Admin> getAdmins() {
		return adminRepos.findAll();
	}

	@Override
	public Admin updateAdmin(Long id, Admin admin) {
		Admin foundedAdmin = adminRepos.findById(id).get();
		foundedAdmin.setNom(admin.getNom());
		foundedAdmin.setPrenom(admin.getPrenom());
		foundedAdmin.setPassword(admin.getPassword());
		return adminRepos.save(foundedAdmin);
	}

	@Override
	public Admin getAdminById(Long id) {
		return adminRepos.findById(id).get();
	}

	@Override
	public void deleteAdmin(Long id) {
		Admin admin= adminRepos.findById(id).get();
		adminRepos.delete(admin);
	}

	@Override
	public void createEtudiant(Etudiant etudiant) {
		etudiantRepos.save(etudiant);

	}

	@Override
	public void assignerGroupeAuFormateur(Groupe groupe, Formateur formateur) {
		Formateur foundedFormateur = formateurRepos.findById(formateur.getId()).get();
		formateur.addGroupe(groupe);
		formateurRepos.save(foundedFormateur);
	}

	@Override
	public void assignerMatiereAuFormateur(Matiere matiere, Formateur formateur) {
		Matiere foundedMatiere = matiereRepos.findById(matiere.getId()).get();
		Formateur foundedFormateur = formateurRepos.findById(formateur.getId()).get();
		foundedFormateur.addMatiere(foundedMatiere);
		formateurRepos.save(foundedFormateur);

	}

	@Override
	public void createMatiere(Matiere matiere) {
		matiereRepos.save(matiere);

	}

	@Override
	public void createGroupe(Groupe groupe) {
		groupeRepos.save(groupe);

	}

	@Override
	public double determinerMoyenneEtudiantParMatiere(Etudiant etudiant, Matiere matiere) {
		Etudiant foundedEtudiant = etudiantRepos.findById(etudiant.getId()).get();
		List<Note> listNote = foundedEtudiant.getListNote();
		double moyenne = 0;
		for (Note note : listNote) {
			if (note.getMatiere().getId() == matiere.getId()) {
				moyenne = note.calculMoyenne();
				break;
			}
		}
		return moyenne;
	}

	@Override
	public void consulterHistoriquePresenceParGroupe(Groupe groupe) {
		Groupe foundedGroupe = groupeRepos.findById(groupe.getId()).get();
		List<Matiere> listMatiere = foundedGroupe.getListMatiere();
		for (Matiere matiere : listMatiere) {
			List<Seance> listSeance = matiere.getListSeance();
			for (Seance seance : listSeance) {
				List<FichePresence> listFichePresence = seance.getListFichePresence();
				for (FichePresence fichePresence : listFichePresence) {

				}
			}
		}

	}

	@Override
	public List<FichePresence> consulterHistoriquePresenceParEtudiant(Etudiant etudiant) {
		List<FichePresence> foundedFichePresence = fichePresenceRepos.findByEtudiant(etudiant);
		return foundedFichePresence;
	}

}
