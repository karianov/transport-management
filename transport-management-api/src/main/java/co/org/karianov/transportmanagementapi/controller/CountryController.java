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
import co.org.karianov.transportmanagementapi.model.entity.CountryEntity;
import co.org.karianov.transportmanagementapi.model.request.NewCountryRequest;
import co.org.karianov.transportmanagementapi.service.MapperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/rest/v1/country")
@Api(tags = { "Country" })
public class CountryController {

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private MapperService mapperService;

	@GetMapping("/{countryId}")
	@ApiOperation(value = "Get one existing country", notes = "REST service to obtain one existing country")
	public ResponseEntity<CountryEntity> getCountryById(@PathVariable Integer countryId) {
		CountryEntity searchedCountry = countryRepo.findByCountryId(countryId);
		return new ResponseEntity<CountryEntity>(searchedCountry, HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get all existing countries", notes = "REST service to obtain all existing countries")
	public ResponseEntity<List<CountryEntity>> getAllCountries() {
		List<CountryEntity> allCountries = countryRepo.findAll();
		return new ResponseEntity<List<CountryEntity>>(allCountries, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create one country", notes = "REST service to insert new countries")
	public ResponseEntity<CountryEntity> createCountry(@RequestBody NewCountryRequest createCountryRequest) {
		CountryEntity countryToCreate = mapperService.map(createCountryRequest, CountryEntity.class);
		CountryEntity createdCountry = countryRepo.save(countryToCreate);
		return new ResponseEntity<CountryEntity>(createdCountry, HttpStatus.CREATED);
	}

	@PutMapping("/{countryId}")
	@ApiOperation(value = "Update a country", notes = "REST service to update a searched country")
	public ResponseEntity<CountryEntity> updateCountry(@PathVariable Integer countryId,
			@RequestBody CountryEntity countryToUpdate) {
		countryToUpdate.setCountryId(countryId);
		CountryEntity updatedCountry = countryRepo.save(countryToUpdate);
		return new ResponseEntity<CountryEntity>(updatedCountry, HttpStatus.OK);
	}

	@DeleteMapping("/{countryId}")
	@ApiOperation(value = "Delete a country", notes = "REST service to delete a searched country")
	public ResponseEntity<CountryEntity> deleteCountry(@PathVariable Integer countryId) {
		CountryEntity deletedCountry = countryRepo.findByCountryId(countryId);
		countryRepo.deleteById(countryId);
		return new ResponseEntity<CountryEntity>(deletedCountry, HttpStatus.OK);
	}

}
