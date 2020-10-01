package co.org.karianov.transportmanagementapi.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.org.karianov.transportmanagementapi.model.entity.CountryEntity;

@Repository
public interface CountryRepo extends JpaRepository<CountryEntity, Integer> {
	
	public CountryEntity findByCountryId(Integer countryId);
	
}
