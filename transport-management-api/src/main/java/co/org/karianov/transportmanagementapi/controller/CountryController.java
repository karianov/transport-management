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

import co.org.karianov.transportmanagementapi.model.entity.CountryEntity;
import co.org.karianov.transportmanagementapi.model.request.NewCountryRequest;
import co.org.karianov.transportmanagementapi.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/rest/v1/country")
@Api(tags = { "Country" })
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping("/{countryId}")
	@ApiOperation(value = "Get one existing country", notes = "REST service to obtain one existing country")
	public ResponseEntity<CountryEntity> getCountryById(@PathVariable Integer countryId) {
		return new ResponseEntity<CountryEntity>(countryService.findCountryById(countryId), HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get all existing countries", notes = "REST service to obtain all existing countries")
	public ResponseEntity<List<CountryEntity>> getAllCountries() {
		return new ResponseEntity<List<CountryEntity>>(countryService.findAllCountries(), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create one country", notes = "REST service to insert new countries")
	public ResponseEntity<CountryEntity> createCountry(@RequestBody NewCountryRequest newCountryRequest) {
		return new ResponseEntity<CountryEntity>(countryService.saveCountry(newCountryRequest), HttpStatus.CREATED);
	}

	@PutMapping("/{countryId}")
	@ApiOperation(value = "Update a country", notes = "REST service to update a searched country")
	public ResponseEntity<CountryEntity> updateCountry(@PathVariable Integer countryId,
			@RequestBody CountryEntity countryToUpdate) {
		return new ResponseEntity<CountryEntity>(countryService.updateCountry(countryId, countryToUpdate),
				HttpStatus.OK);
	}

	@DeleteMapping("/{countryId}")
	@ApiOperation(value = "Delete a country", notes = "REST service to delete a searched country")
	public ResponseEntity<CountryEntity> deleteCountry(@PathVariable Integer countryId) {
		return new ResponseEntity<CountryEntity>(countryService.deleteCountry(countryId), HttpStatus.OK);
	}

}
