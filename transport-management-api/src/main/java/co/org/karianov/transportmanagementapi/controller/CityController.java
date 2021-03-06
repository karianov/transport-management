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

import co.org.karianov.transportmanagementapi.model.entity.CityEntity;
import co.org.karianov.transportmanagementapi.model.request.NewCityRequest;
import co.org.karianov.transportmanagementapi.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/rest/v1/city")
@Api(tags = { "City" })
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping("/{cityId}")
	@ApiOperation(value = "Get one existing city", notes = "REST service to obtain one existing city")
	public ResponseEntity<CityEntity> getCityById(@PathVariable Integer cityId) {
		return new ResponseEntity<CityEntity>(cityService.findCityById(cityId), HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get all existing cities", notes = "REST service to obtain all existing cities")
	public ResponseEntity<List<CityEntity>> getAllCities() {
		return new ResponseEntity<List<CityEntity>>(cityService.findAllCities(), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create one City", notes = "REST service to insert new Citys")
	public ResponseEntity<CityEntity> createCity(@RequestBody NewCityRequest newCityRequest) {
		return new ResponseEntity<CityEntity>(cityService.saveCity(newCityRequest), HttpStatus.CREATED);
	}

	@PutMapping("/{cityId}")
	@ApiOperation(value = "Update a city", notes = "REST service to update a searched city")
	public ResponseEntity<CityEntity> updateCity(@PathVariable Integer cityId, @RequestBody CityEntity cityToUpdate) {
		return new ResponseEntity<CityEntity>(cityService.updateCity(cityId, cityToUpdate), HttpStatus.OK);
	}

	@DeleteMapping("/{cityId}")
	@ApiOperation(value = "Delete a city", notes = "REST service to delete a searched city")
	public ResponseEntity<CityEntity> deleteCity(@PathVariable Integer cityId) {
		return new ResponseEntity<CityEntity>(cityService.deleteCity(cityId), HttpStatus.OK);
	}

}
