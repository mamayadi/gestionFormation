package spring.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spring.jpa.exceptions.NotFoundException;
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
import spring.jpa.repository.NoteRepository;
import spring.jpa.repository.SeanceRepository;
import spring.jpa.service.interfaces.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private PasswordEncoder passwordEncoder;
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
	@Autowired
	private SeanceRepository seanceRepos;
	@Autowired
	private NoteRepository noteRepos;

	public AdminServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Admin createAdmin(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminRepos.save(admin);
	}

	@Override
	public List<Admin> getAdmins() {
		return adminRepos.findAll();
	}

	@Override
	public Admin updateAdmin(Long id, Admin admin) {
		Admin foundedAdmin = adminRepos.findById(id).orElseThrow(() -> new NotFoundException("Admin not found!"));
		if (admin.getNom() != null) {
			foundedAdmin.setNom(admin.getNom());
		}
		if (admin.getPrenom() != null) {
			foundedAdmin.setPrenom(admin.getPrenom());
		}
		if (admin.getPassword() != null) {
			foundedAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
		}
		return adminRepos.save(foundedAdmin);
	}

	@Override
	public Admin getAdminById(Long id) {
		return adminRepos.findById(id).orElseThrow(() -> new NotFoundException("Admin not found!"));
	}

	@Override
	public void deleteAdmin(Long id) {
		Admin admin = adminRepos.findById(id).orElseThrow(() -> new NotFoundException("Admin not found!"));
		adminRepos.delete(admin);
	}

	@Override
	public Formateur assignerGroupeAuFormateur(long groupeId, long formateurId) {
		Formateur foundedFormateur = formateurRepos.findById(formateurId)
				.orElseThrow(() -> new NotFoundException("Formateur not found!"));
		Groupe foundedGroupe = groupeRepos.findById(groupeId)
				.orElseThrow(() -> new NotFoundException("Groupe not found!"));
		foundedFormateur.addGroupe(foundedGroupe);
		return formateurRepos.save(foundedFormateur);
	}

	@Override
	public Formateur assignerMatiereAuFormateur(long matiereId, long formateurId) {
		Matiere foundedMatiere = matiereRepos.findById(matiereId)
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		Formateur foundedFormateur = formateurRepos.findById(formateurId)
				.orElseThrow(() -> new NotFoundException("Formateur not found!"));
		foundedFormateur.addMatiere(foundedMatiere);
		return formateurRepos.save(foundedFormateur);
	}

	@Override
	public double determinerMoyenneEtudiantParMatiere(long etudiantId, long matiereId) {
		Etudiant foundedEtudiant = etudiantRepos.findById(etudiantId)
				.orElseThrow(() -> new NotFoundException("Etudiant not found!"));
		List<Note> listNote = foundedEtudiant.getListNote();
		double moyenne = 0;
		for (Note note : listNote) {
			if (note.getMatiere().getId() == matiereId) {
				moyenne = note.calculMoyenne();
				break;
			}
		}
		return moyenne;
	}

	@Override
	public List<FichePresence> consulterHistoriquePresenceParGroupe(long groupeId) {
		Groupe foundedGroupe = groupeRepos.findById(groupeId)
				.orElseThrow(() -> new NotFoundException("Groupe not found!"));
		List<Matiere> listMatiere = foundedGroupe.getListMatiere();
		List<FichePresence> listFichePresenceFounded = new ArrayList<FichePresence>();
		for (Matiere matiere : listMatiere) {
			List<Seance> listSeance = matiere.getListSeance();
			for (Seance seance : listSeance) {
				List<FichePresence> listFichePresence = seance.getListFichePresence();
				for (FichePresence fichePresence : listFichePresence) {
					listFichePresenceFounded.add(fichePresence);
				}
			}
		}
		return listFichePresenceFounded;
	}

	@Override
	public List<FichePresence> consulterHistoriquePresenceParEtudiant(long etudiantId) {
		Etudiant foundedEtudiant = etudiantRepos.findById(etudiantId)
				.orElseThrow(() -> new NotFoundException("Etudiant not found!"));
		List<FichePresence> foundedFichePresence = fichePresenceRepos.findByEtudiant(foundedEtudiant);
		return foundedFichePresence;
	}

	@Override
	public Etudiant assignerGroupeAuEtudiant(long groupeId, long etudiantId) {
		Groupe foundedGroupe = groupeRepos.findById(groupeId)
				.orElseThrow(() -> new NotFoundException("Groupe not found!"));
		Etudiant foundedEtudiant = etudiantRepos.findById(etudiantId)
				.orElseThrow(() -> new NotFoundException("Etudiant not found!"));
		foundedEtudiant.setGroupe(foundedGroupe);
		return etudiantRepos.save(foundedEtudiant);
	}

	@Override
	public Groupe assignerMatiereAuGroupe(long matiereId, long groupeId) {
		Matiere foundedMatiere = matiereRepos.findById(matiereId)
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		Groupe foundedGroupe = groupeRepos.findById(groupeId)
				.orElseThrow(() -> new NotFoundException("Groupe not found!"));
		foundedGroupe.addMatiere(foundedMatiere);
		return groupeRepos.save(foundedGroupe);
	}

	@Override
	public Matiere ajoutSeancePourMatiere(Seance seance, long matiereId) {
		Seance savedSeance = seanceRepos.save(seance);
		Matiere foundedMatiere = matiereRepos.findById(matiereId)
				.orElseThrow(() -> new NotFoundException("Matiere not found!"));
		foundedMatiere.addSeance(savedSeance);
		return matiereRepos.save(foundedMatiere);
	}

	@Override
	public Etudiant ajouterNoteAuEtudiant(Note note, long etudiantId) {
		Etudiant foundedEtudiant = etudiantRepos.findById(etudiantId)
				.orElseThrow(() -> new NotFoundException("Etudiant not found!"));
		Note savedNote = noteRepos.save(note);
		foundedEtudiant.addNote(savedNote);
		return etudiantRepos.save(foundedEtudiant);
	}

	@Override
	public Seance ajoutFichePresenceAuSeance(FichePresence fichePresence, long seanceId) {
		Seance foundedSeance = seanceRepos.findById(seanceId)
				.orElseThrow(() -> new NotFoundException("Seance not found!"));
		FichePresence savedFichePresence = fichePresenceRepos.save(fichePresence);
		foundedSeance.addFichePresence(savedFichePresence);
		return null;
	}

}
