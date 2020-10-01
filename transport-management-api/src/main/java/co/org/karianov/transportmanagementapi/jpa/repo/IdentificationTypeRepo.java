package co.org.karianov.transportmanagementapi.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.org.karianov.transportmanagementapi.model.entity.IdentificationTypeEntity;

public interface IdentificationTypeRepo extends JpaRepository<IdentificationTypeEntity, Integer> {

	public IdentificationTypeEntity findByIdentificationTypeId(Integer identificationTypeId);
	
}
