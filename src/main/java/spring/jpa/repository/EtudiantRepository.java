package spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.jpa.model.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
