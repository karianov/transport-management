import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Country } from '../../models/api/country';
import { API_BASE_LOCATION } from '../../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class CountryService {
  private endpoint: string;

  constructor(private httpClient: HttpClient) {
    this.endpoint = 'country';
  }

  getCountries(): Observable<Country[]> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    return this.httpClient.get<Country[]>(serviceUrl);
  }

  getCountry(countryId: number): Observable<Country> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + countryId;
    return this.httpClient.get<Country>(serviceUrl);
  }

  createCountry(newCountry: Country): Observable<Country> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    newCountry.countryId = null;
    return this.httpClient.post<Country>(serviceUrl, newCountry);
  }

  updateCountry(
    countryId: number,
    updatedCountry: Country
  ): Observable<Country> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + countryId;
    updatedCountry.countryId = countryId;
    return this.httpClient.post<Country>(serviceUrl, updatedCountry);
  }

  deleteCountry(countryId: number): Observable<Country> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + countryId;
    return this.httpClient.delete<Country>(serviceUrl);
  }
}
