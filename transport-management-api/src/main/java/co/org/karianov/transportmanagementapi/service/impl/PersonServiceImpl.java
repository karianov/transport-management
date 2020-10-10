package co.org.karianov.transportmanagementapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.org.karianov.transportmanagementapi.jpa.repo.PersonRepo;
import co.org.karianov.transportmanagementapi.model.entity.CityEntity;
import co.org.karianov.transportmanagementapi.model.entity.IdentificationTypeEntity;
import co.org.karianov.transportmanagementapi.model.entity.PersonEntity;
import co.org.karianov.transportmanagementapi.model.request.NewPersonRequest;
import co.org.karianov.transportmanagementapi.service.CityService;
import co.org.karianov.transportmanagementapi.service.IdentificationTypeService;
import co.org.karianov.transportmanagementapi.service.MapperService;
import co.org.karianov.transportmanagementapi.service.PersonService;

@Service(value = "personService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepo personRepo;

	@Autowired
	private MapperService mapperService;

	@Autowired
	private IdentificationTypeService identificationTypeService;

	@Autowired
	private CityService cityService;

	@Override
	public PersonEntity findPersonById(Integer personId) {
		PersonEntity searchedPerson = personRepo.findByPersonId(personId);
		if (searchedPerson != null) {
			return searchedPerson;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person " + personId + " does not exists.");
		}
	}

	@Override
	public List<PersonEntity> findAllPeople() {
		return personRepo.findAll();
	}

	@Override
	public PersonEntity savePerson(NewPersonRequest newPersonRequest) {
		IdentificationTypeEntity nestedIdentificationType = identificationTypeService
				.findIdentificationTypeById(newPersonRequest.getIdentificationType().getIdentificationTypeId());
		CityEntity nestedCity = cityService.findCityById(newPersonRequest.getCity().getCityId());
		try {
			PersonEntity personToCreate = mapperService.map(newPersonRequest, PersonEntity.class);
			personToCreate.setIdentificationType(nestedIdentificationType);
			personToCreate.setCity(nestedCity);
			return personRepo.save(personToCreate);
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Person was not able to save due to input values.");
		}
	}

	@Override
	public PersonEntity updatePerson(Integer personId, PersonEntity personToUpdate) {
		findPersonById(personId);
		personToUpdate.setPersonId(personId);
		try {
			return personRepo.save(personToUpdate);
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Person was not able to update due to existing values.");
		}
	}

	@Override
	public PersonEntity deletePerson(Integer personId) {
		PersonEntity deletedPerson = findPersonById(personId);
		try {
			personRepo.deleteById(personId);
			return deletedPerson;
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person " + personId + " was not able to delete.");
		}
	}

}
