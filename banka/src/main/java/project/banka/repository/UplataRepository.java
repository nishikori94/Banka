package project.banka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.banka.model.Uplata;

@Repository
public interface UplataRepository extends JpaRepository<Uplata, Long> {

	Uplata getUplataByUplataLinkContainingAndId(String paymentUrl, Long paymentId);

	Uplata findUplataByUplataId(Long uplataId);

}
