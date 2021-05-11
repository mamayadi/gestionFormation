package spring.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.model.FichePresence;
import spring.jpa.repository.FichePresenceRepository;
import spring.jpa.service.interfaces.FichePresenceService;

@Service
public class FichePresenceServiceImpl implements FichePresenceService {
	@Autowired
	private FichePresenceRepository fichePresenceRepos;

	public FichePresenceServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public FichePresence createFichePresence(FichePresence fichePresence) {
		return fichePresenceRepos.save(fichePresence);
	}

	@Override
	public List<FichePresence> getFichePresences() {
		return fichePresenceRepos.findAll();
	}

	@Override
	public FichePresence getFichePresenceById(Long id) {
		return fichePresenceRepos.findById(id).get();
	}

	@Override
	public FichePresence updateFichePresence(Long id, FichePresence fichePresence) {
		FichePresence foundedFichePresence = getFichePresenceById(id);
		if (fichePresence.getEtudiant() != null) {
			foundedFichePresence.setEtudiant(fichePresence.getEtudiant());
		}
		if (fichePresence.isPresence()) {
			foundedFichePresence.setPresence(fichePresence.isPresence());
		}
		return fichePresenceRepos.save(foundedFichePresence);
	}

	@Override
	public void deleteFichePresence(Long id) {
		fichePresenceRepos.deleteById(id);
	}
}
