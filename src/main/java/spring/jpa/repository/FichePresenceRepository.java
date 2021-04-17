package spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.jpa.model.Etudiant;
import spring.jpa.model.FichePresence;

public interface FichePresenceRepository extends JpaRepository<FichePresence, Long> {
	public List<FichePresence> findByEtudiant(Etudiant etudiant);
}
