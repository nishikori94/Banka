package project.banka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.banka.model.RezultatTransakcije;

public interface RezultatTransakcijeRepository extends JpaRepository<RezultatTransakcije, Long> {

}
