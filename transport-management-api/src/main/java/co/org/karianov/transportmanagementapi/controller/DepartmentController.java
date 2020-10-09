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

import co.org.karianov.transportmanagementapi.model.entity.DepartmentEntity;
import co.org.karianov.transportmanagementapi.model.request.NewDepartmentRequest;
import co.org.karianov.transportmanagementapi.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/rest/v1/department")
@Api(tags = { "Department" })
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/{departmentId}")
	@ApiOperation(value = "Get one existing department", notes = "REST service to obtain one existing department")
	public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable Integer departmentId) {
		return new ResponseEntity<DepartmentEntity>(departmentService.findDepartmentById(departmentId), HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get all existing departments", notes = "REST service to obtain all existing departments")
	public ResponseEntity<List<DepartmentEntity>> getAllDepartments() {
		return new ResponseEntity<List<DepartmentEntity>>(departmentService.findAllDepartments(), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create one department", notes = "REST service to insert new departments")
	public ResponseEntity<DepartmentEntity> createDepartment(@RequestBody NewDepartmentRequest newDepartmentRequest) {
		return new ResponseEntity<DepartmentEntity>(departmentService.saveDepartment(newDepartmentRequest),
				HttpStatus.CREATED);
	}

	@PutMapping("/{departmentId}")
	@ApiOperation(value = "Update a department", notes = "REST service to update a searched department")
	public ResponseEntity<DepartmentEntity> updateDepartment(@PathVariable Integer departmentId,
			@RequestBody DepartmentEntity departmentToUpdate) {
		return new ResponseEntity<DepartmentEntity>(
				departmentService.updateDepartment(departmentId, departmentToUpdate), HttpStatus.OK);
	}

	@DeleteMapping("/{departmentId}")
	@ApiOperation(value = "Delete a department", notes = "REST service to delete a searched department")
	public ResponseEntity<DepartmentEntity> deleteDepartment(@PathVariable Integer departmentId) {
		return new ResponseEntity<DepartmentEntity>(departmentService.deleteDepartment(departmentId), HttpStatus.OK);
	}

}
