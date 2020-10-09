package co.org.karianov.transportmanagementapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.org.karianov.transportmanagementapi.jpa.repo.DepartmentRepo;
import co.org.karianov.transportmanagementapi.model.entity.CountryEntity;
import co.org.karianov.transportmanagementapi.model.entity.DepartmentEntity;
import co.org.karianov.transportmanagementapi.model.request.NewDepartmentRequest;
import co.org.karianov.transportmanagementapi.service.CountryService;
import co.org.karianov.transportmanagementapi.service.DepartmentService;
import co.org.karianov.transportmanagementapi.service.MapperService;

@Service(value = "departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepo departmentRepo;

	@Autowired
	private MapperService mapperService;

	@Autowired
	private CountryService countryService;

	@Override
	public DepartmentEntity findDepartmentById(Integer departmentId) {
		DepartmentEntity searchedDepartment = departmentRepo.findByDepartmentId(departmentId);
		if (searchedDepartment != null) {
			return searchedDepartment;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department " + departmentId + " does not exist.");
		}
	}

	@Override
	public List<DepartmentEntity> findAllDepartments() {
		return departmentRepo.findAll();
	}

	@Override
	public DepartmentEntity saveDepartment(NewDepartmentRequest newDepartmentRequest) {
		CountryEntity nestedCountry = countryService.findCountryById(newDepartmentRequest.getCountry().getCountryId());
		try {
			DepartmentEntity departmentToCreate = mapperService.map(newDepartmentRequest, DepartmentEntity.class);
			departmentToCreate.setCountry(nestedCountry);
			return departmentRepo.save(departmentToCreate);
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Department was not able to save due to input values.");
		}
	}

	@Override
	public DepartmentEntity updateDepartment(Integer departmentId, DepartmentEntity departmentToUpdate) {
		findDepartmentById(departmentId);
		departmentToUpdate.setDepartmentId(departmentId);
		try {
			return departmentRepo.save(departmentToUpdate);
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"Department was not able to update due to existing values.");
		}
	}

	@Override
	public DepartmentEntity deleteDepartment(Integer departmentId) {
		DepartmentEntity deletedDepartment = findDepartmentById(departmentId);
		try {
			departmentRepo.deleteById(departmentId);
			return deletedDepartment;
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Department " + departmentId + " was not able to delete.");
		}
	}

}
