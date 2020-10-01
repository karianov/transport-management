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
import co.org.karianov.transportmanagementapi.jpa.repo.CompanyRepo;
import co.org.karianov.transportmanagementapi.jpa.repo.IdentificationTypeRepo;
import co.org.karianov.transportmanagementapi.jpa.repo.PersonRepo;
import co.org.karianov.transportmanagementapi.model.entity.CityEntity;
import co.org.karianov.transportmanagementapi.model.entity.CompanyEntity;
import co.org.karianov.transportmanagementapi.model.entity.IdentificationTypeEntity;
import co.org.karianov.transportmanagementapi.model.entity.PersonEntity;
import co.org.karianov.transportmanagementapi.model.request.NewCompanyRequest;
import co.org.karianov.transportmanagementapi.service.MapperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/rest/v1/company")
@Api(tags = { "Company" })
public class CompanyController {

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private MapperService mapperService;

	@Autowired
	private IdentificationTypeRepo identificationTypeRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private PersonRepo personRepo;

	@GetMapping("/{companyId}")
	@ApiOperation(value = "Get one existing company", notes = "REST service to obtain one existing company")
	public ResponseEntity<CompanyEntity> getCompanyById(@PathVariable Integer companyId) {
		CompanyEntity searchedCompany = companyRepo.findByCompanyId(companyId);
		return new ResponseEntity<CompanyEntity>(searchedCompany, HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "Get all existing companies", notes = "REST service to obtain all existing companies")
	public ResponseEntity<List<CompanyEntity>> getAllCities() {
		List<CompanyEntity> allCities = companyRepo.findAll();
		return new ResponseEntity<List<CompanyEntity>>(allCities, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Create one company", notes = "REST service to insert new companies")
	public ResponseEntity<CompanyEntity> createCompany(@RequestBody NewCompanyRequest createCompanyRequest) {
		IdentificationTypeEntity nestedIdentificationType = identificationTypeRepo
				.findByIdentificationTypeId(createCompanyRequest.getIdentificationTypeId());
		CityEntity nestedCity = cityRepo.findByCityId(createCompanyRequest.getCityId());
		PersonEntity nestedLegalRepresentative = personRepo
				.findByPersonId(createCompanyRequest.getLegalRepresentativeId());
		CompanyEntity companyToCreate = mapperService.map(createCompanyRequest, CompanyEntity.class);
		companyToCreate.setIdentificationType(nestedIdentificationType);
		companyToCreate.setCity(nestedCity);
		companyToCreate.setLegalRepresentative(nestedLegalRepresentative);
		CompanyEntity createdCompany = companyRepo.save(companyToCreate);
		return new ResponseEntity<CompanyEntity>(createdCompany, HttpStatus.CREATED);
	}

	@PutMapping("/{companyId}")
	@ApiOperation(value = "Update a company", notes = "REST service to update a searched company")
	public ResponseEntity<CompanyEntity> updateCompany(@PathVariable Integer companyId,
			@RequestBody CompanyEntity companyToUpdate) {
		companyToUpdate.setCompanyId(companyId);
		CompanyEntity updatedCompany = companyRepo.save(companyToUpdate);
		return new ResponseEntity<CompanyEntity>(updatedCompany, HttpStatus.OK);
	}

	@DeleteMapping("/{companyId}")
	@ApiOperation(value = "Delete a company", notes = "REST service to delete a searched company")
	public ResponseEntity<CompanyEntity> deleteCompany(@PathVariable Integer companyId) {
		CompanyEntity deletedCompany = companyRepo.findByCompanyId(companyId);
		companyRepo.deleteById(companyId);
		return new ResponseEntity<CompanyEntity>(deletedCompany, HttpStatus.OK);
	}

}
