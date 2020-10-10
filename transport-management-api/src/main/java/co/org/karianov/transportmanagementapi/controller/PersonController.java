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

import co.org.karianov.transportmanagementapi.model.entity.PersonEntity;
import co.org.karianov.transportmanagementapi.model.request.NewPersonRequest;
import co.org.karianov.transportmanagementapi.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/rest/v1/person")
@Api(tags = { "Person" })
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/{personId}")
	@ApiOperation(value = "Get one existing person", notes = "REST service to obtain one existing person")
	public ResponseEntity<PersonEntity> getPersonById(@PathVariable Integer personId) {
		return new ResponseEntity<PersonEntity>(personService.findPersonById(personId), HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get all existing people", notes = "REST service to obtain all existing people")
	public ResponseEntity<List<PersonEntity>> getAllCities() {
		return new ResponseEntity<List<PersonEntity>>(personService.findAllPeople(), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create one person", notes = "REST service to insert new people")
	public ResponseEntity<PersonEntity> createPerson(@RequestBody NewPersonRequest newPersonRequest) {
		return new ResponseEntity<PersonEntity>(personService.savePerson(newPersonRequest), HttpStatus.CREATED);
	}

	@PutMapping("/{personId}")
	@ApiOperation(value = "Update a person", notes = "REST service to update a searched person")
	public ResponseEntity<PersonEntity> updatePerson(@PathVariable Integer personId,
			@RequestBody PersonEntity personToUpdate) {
		return new ResponseEntity<PersonEntity>(personService.updatePerson(personId, personToUpdate), HttpStatus.OK);
	}

	@DeleteMapping("/{personId}")
	@ApiOperation(value = "Delete a person", notes = "REST service to delete a searched person")
	public ResponseEntity<PersonEntity> deletePerson(@PathVariable Integer personId) {
		return new ResponseEntity<PersonEntity>(personService.deletePerson(personId), HttpStatus.OK);
	}

}
