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

import co.org.karianov.transportmanagementapi.jpa.repo.CountryRepo;
import co.org.karianov.transportmanagementapi.jpa.repo.DepartmentRepo;
import co.org.karianov.transportmanagementapi.model.entity.CountryEntity;
import co.org.karianov.transportmanagementapi.model.entity.DepartmentEntity;
import co.org.karianov.transportmanagementapi.model.request.NewDepartmentRequest;
import co.org.karianov.transportmanagementapi.service.MapperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/rest/v1/department")
@Api(tags = { "Department" })
public class DepartmentController {

	@Autowired
	private DepartmentRepo departmentRepo;

	@Autowired
	private MapperService mapperService;
	
	@Autowired
	private CountryRepo countryRepo;

	@GetMapping("/{departmentId}")
	@ApiOperation(value = "Get one existing department", notes = "REST service to obtain one existing department")
	public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable Integer departmentId) {
		DepartmentEntity searchedDepartment = departmentRepo.findByDepartmentId(departmentId);
		return new ResponseEntity<DepartmentEntity>(searchedDepartment, HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get all existing departments", notes = "REST service to obtain all existing departments")
	public ResponseEntity<List<DepartmentEntity>> getAllDepartments() {
		List<DepartmentEntity> allDepartments = departmentRepo.findAll();
		return new ResponseEntity<List<DepartmentEntity>>(allDepartments, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create one department", notes = "REST service to insert new departments")
	public ResponseEntity<DepartmentEntity> createDepartment(@RequestBody NewDepartmentRequest createDepartmentRequest) {
		CountryEntity nestedCountry = countryRepo.findByCountryId(createDepartmentRequest.getCountry().getCountryId());
		DepartmentEntity departmentToCreate = mapperService.map(createDepartmentRequest, DepartmentEntity.class);
		departmentToCreate.setCountry(nestedCountry);
		DepartmentEntity createdDepartment = departmentRepo.save(departmentToCreate);
		return new ResponseEntity<DepartmentEntity>(createdDepartment, HttpStatus.CREATED);
	}

	@PutMapping("/{departmentId}")
	@ApiOperation(value = "Update a department", notes = "REST service to update a searched department")
	public ResponseEntity<DepartmentEntity> updateDepartment(@PathVariable Integer departmentId,
			@RequestBody DepartmentEntity departmentToUpdate) {
		departmentToUpdate.setDepartmentId(departmentId);
		DepartmentEntity updatedDepartment = departmentRepo.save(departmentToUpdate);
		return new ResponseEntity<DepartmentEntity>(updatedDepartment, HttpStatus.OK);
	}

	@DeleteMapping("/{departmentId}")
	@ApiOperation(value = "Delete a department", notes = "REST service to delete a searched department")
	public ResponseEntity<DepartmentEntity> deleteDepartment(@PathVariable Integer departmentId) {
		DepartmentEntity deletedDepartment = departmentRepo.findByDepartmentId(departmentId);
		departmentRepo.deleteById(departmentId);
		return new ResponseEntity<DepartmentEntity>(deletedDepartment, HttpStatus.OK);
	}

}
