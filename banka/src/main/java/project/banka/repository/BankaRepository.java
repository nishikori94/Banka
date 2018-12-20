package project.banka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.banka.model.Banka;

public interface BankaRepository extends JpaRepository<Banka, Long>{

}
