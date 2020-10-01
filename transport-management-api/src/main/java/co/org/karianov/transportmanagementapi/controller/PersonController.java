package co.org.karianov.transportmanagementapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.org.karianov.transportmanagementapi.jpa.repo.CityRepo;
import co.org.karianov.transportmanagementapi.jpa.repo.IdentificationTypeRepo;
import co.org.karianov.transportmanagementapi.jpa.repo.PersonRepo;
import co.org.karianov.transportmanagementapi.model.entity.CityEntity;
import co.org.karianov.transportmanagementapi.model.entity.IdentificationTypeEntity;
import co.org.karianov.transportmanagementapi.model.entity.PersonEntity;
import co.org.karianov.transportmanagementapi.model.request.NewPersonRequest;
import co.org.karianov.transportmanagementapi.service.MapperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/rest/v1/person")
@Api(tags = { "Person" })
public class PersonController {

	@Autowired
	private PersonRepo personRepo;

	@Autowired
	private MapperService mapperService;
	
	@Autowired
	private IdentificationTypeRepo identificationTypeRepo;

	@Autowired
	private CityRepo cityRepo;
	
	@GetMapping("/{personId}")
	@ApiOperation(value = "Get one existing person", notes = "REST service to obtain one existing person")
	public ResponseEntity<PersonEntity> getPersonById(@PathVariable Integer personId) {
		PersonEntity searchedPerson = personRepo.findByPersonId(personId);
		return new ResponseEntity<PersonEntity>(searchedPerson, HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get all existing people", notes = "REST service to obtain all existing people")
	public ResponseEntity<List<PersonEntity>> getAllCities() {
		List<PersonEntity> allPeople = personRepo.findAll();
		return new ResponseEntity<List<PersonEntity>>(allPeople, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create one person", notes = "REST service to insert new people")
	public ResponseEntity<PersonEntity> createPerson(
			@RequestBody NewPersonRequest createPersonRequest) {
		IdentificationTypeEntity nestedIdentificationType = identificationTypeRepo.findByIdentificationTypeId(createPersonRequest.getIdentificationType().getIdentificationTypeId());
		CityEntity nestedCity = cityRepo.findByCityId(createPersonRequest.getCity().getCityId());
		PersonEntity personToCreate = mapperService.map(createPersonRequest, PersonEntity.class);
		personToCreate.setIdentificationType(nestedIdentificationType);
		personToCreate.setCity(nestedCity);
		PersonEntity createdPerson = personRepo.save(personToCreate);
		return new ResponseEntity<PersonEntity>(createdPerson, HttpStatus.CREATED);
	}

	@PutMapping("/{personId}")
	@ApiOperation(value = "Update a person", notes = "REST service to update a searched person")
	public ResponseEntity<PersonEntity> updatePerson(@PathVariable Integer personId,
			@RequestBody PersonEntity personToUpdate) {
		personToUpdate.setPersonId(personId);
		PersonEntity updatedPerson = personRepo.save(personToUpdate);
		return new ResponseEntity<PersonEntity>(updatedPerson, HttpStatus.OK);
	}

	@DeleteMapping("/{personId}")
	@ApiOperation(value = "Delete a person", notes = "REST service to delete a searched person")
	public ResponseEntity<PersonEntity> deletePerson(@PathVariable Integer personId) {
		PersonEntity deletedPerson = personRepo.findByPersonId(personId);
		personRepo.deleteById(personId);
		return new ResponseEntity<PersonEntity>(deletedPerson, HttpStatus.OK);
	}
	
}
