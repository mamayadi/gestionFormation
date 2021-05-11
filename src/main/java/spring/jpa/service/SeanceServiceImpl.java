package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.model.Seance;
import spring.jpa.repository.SeanceRepository;
import spring.jpa.service.interfaces.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired
	private SeanceRepository noteRepos;

	public SeanceServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public Seance createSeance(Seance note) {
		return noteRepos.save(note);
	}

	public List<Seance> getSeances() {
		return noteRepos.findAll();
	}

	public Seance getSeanceById(Long id) {
		return noteRepos.findById(id).get();
	}

	public Seance updateSeance(Long id, Seance note) {
		Seance foundedSeance = getSeanceById(id);
		if (note.getDate() != null) {
			foundedSeance.setDate(note.getDate());
		}
		if (note.getDescription() != null) {
			foundedSeance.setDescription(note.getDescription());
		}
		if (note.getDuree() != null) {
			foundedSeance.setDuree(note.getDuree());
		}
		if (note.getListFichePresence() != null) {
			foundedSeance.setListFichePresence(note.getListFichePresence());
		}
		return noteRepos.save(foundedSeance);
	}

	public void deleteSeance(Long id) {
		noteRepos.deleteById(id);
	}
}
