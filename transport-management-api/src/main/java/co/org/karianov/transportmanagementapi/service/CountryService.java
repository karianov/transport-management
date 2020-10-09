package co.org.karianov.transportmanagementapi.service;

import java.util.List;

import co.org.karianov.transportmanagementapi.model.entity.CountryEntity;
import co.org.karianov.transportmanagementapi.model.request.NewCountryRequest;

public interface CountryService {
	
	public CountryEntity findCountryById(Integer countryId);

	public List<CountryEntity> findAllCountries();

	public CountryEntity saveCountry(NewCountryRequest newCountryRequest);

	public CountryEntity updateCountry(Integer countryId, CountryEntity countryToUpdate);

	public CountryEntity deleteCountry(Integer countryId);

}
