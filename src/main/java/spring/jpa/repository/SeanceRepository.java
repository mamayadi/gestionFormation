package spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.jpa.model.Seance;

public interface SeanceRepository extends JpaRepository<Seance, Long> {

}
