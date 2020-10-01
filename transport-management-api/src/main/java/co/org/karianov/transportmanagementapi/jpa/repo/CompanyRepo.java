package co.org.karianov.transportmanagementapi.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.org.karianov.transportmanagementapi.model.entity.CompanyEntity;

public interface CompanyRepo extends JpaRepository<CompanyEntity, Integer> {

	public CompanyEntity findByCompanyId(Integer companyId);

}
