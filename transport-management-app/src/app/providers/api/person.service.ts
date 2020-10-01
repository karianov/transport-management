import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Person } from '../../models/api/person';
import { API_BASE_LOCATION } from '../../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class PersonService {
  private endpoint: string;

  constructor(private httpClient: HttpClient) {
    this.endpoint = 'person';
  }

  getPeople(): Observable<Person[]> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    return this.httpClient.get<Person[]>(serviceUrl);
  }

  getPerson(personId: number): Observable<Person> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + personId;
    return this.httpClient.get<Person>(serviceUrl);
  }

  createPerson(newPerson: Person): Observable<Person> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    newPerson.personId = null;
    return this.httpClient.post<Person>(serviceUrl, newPerson);
  }

  updatePerson(personId: number, updatedPerson: Person): Observable<Person> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + personId;
    updatedPerson.personId = personId;
    return this.httpClient.post<Person>(serviceUrl, updatedPerson);
  }

  deletePerson(personId: number): Observable<Person> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + personId;
    return this.httpClient.delete<Person>(serviceUrl);
  }
}
