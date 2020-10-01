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

import co.org.karianov.transportmanagementapi.jpa.repo.IdentificationTypeRepo;
import co.org.karianov.transportmanagementapi.model.entity.IdentificationTypeEntity;
import co.org.karianov.transportmanagementapi.model.request.NewIdentificationTypeRequest;
import co.org.karianov.transportmanagementapi.service.MapperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/rest/v1/identification_type")
@Api(tags = { "Identification Type" })
public class IdentificationTypeController {

	@Autowired
	private IdentificationTypeRepo identificationTypeRepo;
	
	@Autowired
	private MapperService mapperService;

	@GetMapping("/{identificationTypeId}")
	@ApiOperation(value = "Get one existing identification type", notes = "REST service to obtain one existing identification type")
	public ResponseEntity<IdentificationTypeEntity> getIdentificationTypeById(@PathVariable Integer identificationTypeId) {
		IdentificationTypeEntity searchedIdentificationType = identificationTypeRepo.findByIdentificationTypeId(identificationTypeId);
		return new ResponseEntity<IdentificationTypeEntity>(searchedIdentificationType, HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get all existing identification types", notes = "REST service to obtain all existing identification types")
	public ResponseEntity<List<IdentificationTypeEntity>> getAllIdentificationTypes() {
		List<IdentificationTypeEntity> allIdentificationTypes = identificationTypeRepo.findAll();
		return new ResponseEntity<List<IdentificationTypeEntity>>(allIdentificationTypes, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create one identification type", notes = "REST service to insert new identification types")
	public ResponseEntity<IdentificationTypeEntity> createIdentificationType(@RequestBody NewIdentificationTypeRequest createIdentificationTypeRequest) {
		IdentificationTypeEntity identificationTypeToCreate = mapperService.map(createIdentificationTypeRequest, IdentificationTypeEntity.class);
		IdentificationTypeEntity createdIdentificationType = identificationTypeRepo.save(identificationTypeToCreate);
		return new ResponseEntity<IdentificationTypeEntity>(createdIdentificationType, HttpStatus.CREATED);
	}

	@PutMapping("/{identificationTypeId}")
	@ApiOperation(value = "Update an identification type", notes = "REST service to update a searched identification type")
	public ResponseEntity<IdentificationTypeEntity> updateIdentificationType(@PathVariable Integer identificationTypeId,
			@RequestBody IdentificationTypeEntity identificationTypeToUpdate) {
		identificationTypeToUpdate.setIdentificationTypeId(identificationTypeId);
		IdentificationTypeEntity updatedIdentificationType = identificationTypeRepo.save(identificationTypeToUpdate);
		return new ResponseEntity<IdentificationTypeEntity>(updatedIdentificationType, HttpStatus.OK);
	}

	@DeleteMapping("/{identificationTypeId}")
	@ApiOperation(value = "Delete an identification type", notes = "REST service to delete a searched identification type")
	public ResponseEntity<IdentificationTypeEntity> deleteIdentificationType(@PathVariable Integer identificationTypeId) {
		IdentificationTypeEntity deletedIdentificationType = identificationTypeRepo.findByIdentificationTypeId(identificationTypeId);
		identificationTypeRepo.deleteById(identificationTypeId);
		return new ResponseEntity<IdentificationTypeEntity>(deletedIdentificationType, HttpStatus.OK);
	}
	
}
