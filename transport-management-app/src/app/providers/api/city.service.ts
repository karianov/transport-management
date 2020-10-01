import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { City } from '../../models/api/city';
import { API_BASE_LOCATION } from '../../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class CityService {
  private endpoint: string;

  constructor(private httpClient: HttpClient) {
    this.endpoint = 'city';
  }

  getCities(): Observable<City[]> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    return this.httpClient.get<City[]>(serviceUrl);
  }

  getCity(cityId: number): Observable<City> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + cityId;
    return this.httpClient.get<City>(serviceUrl);
  }

  createCity(newCity: City): Observable<City> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    newCity.cityId = null;
    return this.httpClient.post<City>(serviceUrl, newCity);
  }

  updateCity(cityId: number, updatedCity: City): Observable<City> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + cityId;
    updatedCity.cityId = cityId;
    return this.httpClient.post<City>(serviceUrl, updatedCity);
  }

  deleteCity(cityId: number): Observable<City> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + cityId;
    return this.httpClient.delete<City>(serviceUrl);
  }

}
