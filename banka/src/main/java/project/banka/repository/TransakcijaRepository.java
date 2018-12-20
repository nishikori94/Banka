package project.banka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.banka.model.Transakcija;

public interface TransakcijaRepository extends JpaRepository<Transakcija, Long> {

}
