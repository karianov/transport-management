package co.org.karianov.transportmanagementapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.org.karianov.transportmanagementapi.jpa.repo.CityRepo;
import co.org.karianov.transportmanagementapi.model.entity.CityEntity;
import co.org.karianov.transportmanagementapi.model.entity.DepartmentEntity;
import co.org.karianov.transportmanagementapi.model.request.NewCityRequest;
import co.org.karianov.transportmanagementapi.service.CityService;
import co.org.karianov.transportmanagementapi.service.DepartmentService;
import co.org.karianov.transportmanagementapi.service.MapperService;

@Service(value = "cityService")
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private MapperService mapperService;

	@Autowired
	private DepartmentService departmentService;

	@Override
	public CityEntity findCityById(Integer cityId) {
		CityEntity searchedCity = cityRepo.findByCityId(cityId);
		if (searchedCity != null) {
			return searchedCity;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City " + cityId + " does not exist.");
		}
	}

	@Override
	public List<CityEntity> findAllCities() {
		return cityRepo.findAll();
	}

	@Override
	public CityEntity saveCity(NewCityRequest newCityRequest) {
		DepartmentEntity nestedDepartment = departmentService
				.findDepartmentById(newCityRequest.getDepartment().getDepartmentId());
		try {
			CityEntity cityToCreate = mapperService.map(newCityRequest, CityEntity.class);
			cityToCreate.setDepartment(nestedDepartment);
			return cityRepo.save(cityToCreate);
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City was not able to save due to input values.");
		}
	}

	@Override
	public CityEntity updateCity(Integer cityId, CityEntity cityToUpdate) {
		findCityById(cityId);
		cityToUpdate.setCityId(cityId);
		try {
			return cityRepo.save(cityToUpdate);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"City was not able to update due to existing values.");
		}
	}

	@Override
	public CityEntity deleteCity(Integer cityId) {
		CityEntity deletedCity = findCityById(cityId);
		try {
			cityRepo.deleteById(cityId);
			return deletedCity;
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City " + cityId + " was not able to delete.");
		}
	}

}
