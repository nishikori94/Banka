package project.banka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.banka.model.Racun;

public interface RacunRepository extends JpaRepository<Racun, Long>{

	Racun findByBrojRacuna(String pan);

}
