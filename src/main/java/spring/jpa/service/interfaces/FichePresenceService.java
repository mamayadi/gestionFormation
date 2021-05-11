package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.FichePresence;

public interface FichePresenceService {
	public abstract FichePresence createFichePresence(FichePresence fichePresence);

	public abstract List<FichePresence> getFichePresences();

	public abstract FichePresence getFichePresenceById(Long id);

	public abstract FichePresence updateFichePresence(Long id, FichePresence fichePresence);

	public abstract void deleteFichePresence(Long id);
}
