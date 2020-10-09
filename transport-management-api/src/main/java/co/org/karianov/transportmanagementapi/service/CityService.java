package co.org.karianov.transportmanagementapi.service;

import java.util.List;

import co.org.karianov.transportmanagementapi.model.entity.CityEntity;
import co.org.karianov.transportmanagementapi.model.request.NewCityRequest;

public interface CityService {

	public CityEntity findCityById(Integer cityId);

	public List<CityEntity> findAllCities();

	public CityEntity saveCity(NewCityRequest newCityRequest);

	public CityEntity updateCity(Integer cityId, CityEntity cityToUpdate);

	public CityEntity deleteCity(Integer cityId);

}
