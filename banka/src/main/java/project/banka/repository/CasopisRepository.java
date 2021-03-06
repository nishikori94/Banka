package project.banka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.banka.model.Casopis;

public interface CasopisRepository extends JpaRepository<Casopis, Long>{
	
	public Casopis findCasopisByMerchantId(String merchantId);

}
