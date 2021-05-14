package spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.jpa.model.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{
	Personne findByUsername(String username);
}
