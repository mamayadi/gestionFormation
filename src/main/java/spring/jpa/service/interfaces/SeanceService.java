package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Seance;

public interface SeanceService {
	public abstract Seance createSeance(Seance seance);

	public abstract List<Seance> getSeances();

	public abstract Seance getSeanceById(Long id);

	public abstract Seance updateSeance(Long id, Seance seance);

	public abstract void deleteSeance(Long id);
}
