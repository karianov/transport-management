package co.org.karianov.transportmanagementapi.service;

import java.util.List;

import co.org.karianov.transportmanagementapi.model.entity.PersonEntity;
import co.org.karianov.transportmanagementapi.model.request.NewPersonRequest;

public interface PersonService {

	public PersonEntity findPersonById(Integer personId);

	public List<PersonEntity> findAllPeople();

	public PersonEntity savePerson(NewPersonRequest newPersonRequest);

	public PersonEntity updatePerson(Integer personId, PersonEntity personToUpdate);

	public PersonEntity deletePerson(Integer personId);

}
