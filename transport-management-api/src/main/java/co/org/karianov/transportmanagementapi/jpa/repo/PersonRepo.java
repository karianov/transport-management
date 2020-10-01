package co.org.karianov.transportmanagementapi.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.org.karianov.transportmanagementapi.model.entity.PersonEntity;

public interface PersonRepo extends JpaRepository<PersonEntity, Integer> {

	public PersonEntity findByPersonId(Integer personId);

}
