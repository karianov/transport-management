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

import co.org.karianov.transportmanagementapi.model.entity.IdentificationTypeEntity;
import co.org.karianov.transportmanagementapi.model.request.NewIdentificationTypeRequest;
import co.org.karianov.transportmanagementapi.service.IdentificationTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/rest/v1/identification_type")
@Api(tags = { "Identification Type" })
public class IdentificationTypeController {

	@Autowired
	private IdentificationTypeService identificationTypeService;

	@GetMapping("/{identificationTypeId}")
	@ApiOperation(value = "Get one existing identification type", notes = "REST service to obtain one existing identification type")
	public ResponseEntity<IdentificationTypeEntity> getIdentificationTypeById(
			@PathVariable Integer identificationTypeId) {
		return new ResponseEntity<IdentificationTypeEntity>(
				identificationTypeService.findIdentificationTypeById(identificationTypeId), HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get all existing identification types", notes = "REST service to obtain all existing identification types")
	public ResponseEntity<List<IdentificationTypeEntity>> getAllIdentificationTypes() {
		return new ResponseEntity<List<IdentificationTypeEntity>>(
				identificationTypeService.findAllIdentificationTypes(), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create one identification type", notes = "REST service to insert new identification types")
	public ResponseEntity<IdentificationTypeEntity> createIdentificationType(
			@RequestBody NewIdentificationTypeRequest newIdentificationTypeRequest) {
		return new ResponseEntity<IdentificationTypeEntity>(
				identificationTypeService.saveIdentificationType(newIdentificationTypeRequest), HttpStatus.OK);
	}

	@PutMapping("/{identificationTypeId}")
	@ApiOperation(value = "Update an identification type", notes = "REST service to update a searched identification type")
	public ResponseEntity<IdentificationTypeEntity> updateIdentificationType(@PathVariable Integer identificationTypeId,
			@RequestBody IdentificationTypeEntity identificationTypeToUpdate) {
		return new ResponseEntity<IdentificationTypeEntity>(
				identificationTypeService.updateIdentificationType(identificationTypeId, identificationTypeToUpdate),
				HttpStatus.OK);
	}

	@DeleteMapping("/{identificationTypeId}")
	@ApiOperation(value = "Delete an identification type", notes = "REST service to delete a searched identification type")
	public ResponseEntity<IdentificationTypeEntity> deleteIdentificationType(
			@PathVariable Integer identificationTypeId) {
		return new ResponseEntity<IdentificationTypeEntity>(
				identificationTypeService.deleteIdentificationType(identificationTypeId), HttpStatus.OK);
	}

}
