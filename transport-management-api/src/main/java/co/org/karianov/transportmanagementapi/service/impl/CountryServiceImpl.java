package co.org.karianov.transportmanagementapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.org.karianov.transportmanagementapi.jpa.repo.CountryRepo;
import co.org.karianov.transportmanagementapi.model.entity.CountryEntity;
import co.org.karianov.transportmanagementapi.model.request.NewCountryRequest;
import co.org.karianov.transportmanagementapi.service.CountryService;
import co.org.karianov.transportmanagementapi.service.MapperService;

@Service(value = "countryService")
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private MapperService mapperService;

	@Override
	public CountryEntity findCountryById(Integer countryId) {
		CountryEntity searchedCountry = countryRepo.findByCountryId(countryId);
		if (searchedCountry != null) {
			return searchedCountry;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country " + countryId + " does not exist.");
		}
	}

	@Override
	public List<CountryEntity> findAllCountries() {
		return countryRepo.findAll();
	}

	@Override
	public CountryEntity saveCountry(NewCountryRequest newCountryRequest) {
		try {
			CountryEntity countryToCreate = mapperService.map(newCountryRequest, CountryEntity.class);
			return countryRepo.save(countryToCreate);
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Country was not able to save due to input values.");
		}
	}

	@Override
	public CountryEntity updateCountry(Integer countryId, CountryEntity countryToUpdate) {
		findCountryById(countryId);
		countryToUpdate.setCountryId(countryId);
		try {
			return countryRepo.save(countryToUpdate);
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"Country was not able to update due to existing values.");
		}
	}

	@Override
	public CountryEntity deleteCountry(Integer countryId) {
		CountryEntity deletedCountry = findCountryById(countryId);
		try {
			countryRepo.deleteById(countryId);
			return deletedCountry;
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Country " + countryId + " was not able to delete.");
		}
	}

}
