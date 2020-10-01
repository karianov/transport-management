package co.org.karianov.transportmanagementapi.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.org.karianov.transportmanagementapi.model.entity.CityEntity;

public interface CityRepo extends JpaRepository<CityEntity, Integer> {

	public CityEntity findByCityId(Integer cityId);

}
