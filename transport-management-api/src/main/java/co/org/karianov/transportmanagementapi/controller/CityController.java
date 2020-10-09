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
import org.springframework.web.server.ResponseStatusException;

import co.org.karianov.transportmanagementapi.jpa.repo.CityRepo;
import co.org.karianov.transportmanagementapi.jpa.repo.DepartmentRepo;
import co.org.karianov.transportmanagementapi.model.entity.CityEntity;
import co.org.karianov.transportmanagementapi.model.entity.DepartmentEntity;
import co.org.karianov.transportmanagementapi.model.request.NewCityRequest;
import co.org.karianov.transportmanagementapi.service.MapperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/rest/v1/city")
@Api(tags = { "City" })
public class CityController {

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private MapperService mapperService;

	@Autowired
	private DepartmentRepo departmentRepo;

	@GetMapping("/{cityId}")
	@ApiOperation(value = "Get one existing city", notes = "REST service to obtain one existing city")
	public ResponseEntity<CityEntity> getCityById(@PathVariable Integer cityId) {
		CityEntity searchedCity = cityRepo.findByCityId(cityId);
		if (searchedCity != null) {
			return new ResponseEntity<CityEntity>(searchedCity, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City does not exist");
		}
	}

	@GetMapping
	@ApiOperation(value = "Get all existing cities", notes = "REST service to obtain all existing cities")
	public ResponseEntity<List<CityEntity>> getAllCities() {
		List<CityEntity> allCities = cityRepo.findAll();
		return new ResponseEntity<List<CityEntity>>(allCities, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create one City", notes = "REST service to insert new Citys")
	public ResponseEntity<CityEntity> createCity(@RequestBody NewCityRequest createCityRequest) {
		try {
			DepartmentEntity nestedDepartment = departmentRepo
					.findByDepartmentId(createCityRequest.getDepartment().getDepartmentId());
			CityEntity cityToCreate = mapperService.map(createCityRequest, CityEntity.class);
			cityToCreate.setDepartment(nestedDepartment);
			CityEntity createdCity = cityRepo.save(cityToCreate);
			return new ResponseEntity<CityEntity>(createdCity, HttpStatus.CREATED);
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"This city was not able to save due to input values.");
		}
	}

	@PutMapping("/{cityId}")
	@ApiOperation(value = "Update a city", notes = "REST service to update a searched city")
	public ResponseEntity<CityEntity> updateCity(@PathVariable Integer cityId, @RequestBody CityEntity cityToUpdate) {
		cityToUpdate.setCityId(cityId);
		try {
			CityEntity updatedCity = cityRepo.save(cityToUpdate);
			return new ResponseEntity<CityEntity>(updatedCity, HttpStatus.OK);
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"This city was not able to update due to existing values.");
		}
	}

	@DeleteMapping("/{cityId}")
	@ApiOperation(value = "Delete a city", notes = "REST service to delete a searched city")
	public ResponseEntity<CityEntity> deleteCity(@PathVariable Integer cityId) {
		try {
			CityEntity deletedCity = cityRepo.findByCityId(cityId);
			cityRepo.deleteById(cityId);
			return new ResponseEntity<CityEntity>(deletedCity, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This city was not able to delete.");
		}
	}

}
