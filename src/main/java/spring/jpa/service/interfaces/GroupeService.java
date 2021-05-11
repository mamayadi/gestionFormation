package spring.jpa.service.interfaces;

import java.util.List;

import spring.jpa.model.Groupe;

public interface GroupeService {
	public abstract Groupe createGroupe(Groupe groupe);

	public abstract List<Groupe> getGroupes();

	public abstract Groupe getGroupeById(Long id);

	public abstract Groupe updateGroupe(Long id, Groupe groupe);

	public abstract void deleteGroupe(Long id);
}
