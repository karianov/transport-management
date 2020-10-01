import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IdentificationType } from '../../models/api/identification-type';
import { API_BASE_LOCATION } from '../../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class IdentificationTypeService {
  private endpoint: string;

  constructor(private httpClient: HttpClient) {
    this.endpoint = 'identification_type';
  }

  getIdentificationTypes(): Observable<IdentificationType[]> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    return this.httpClient.get<IdentificationType[]>(serviceUrl);
  }

  getIdentificationType(identificationTypeId: number): Observable<IdentificationType> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + identificationTypeId;
    return this.httpClient.get<IdentificationType>(serviceUrl);
  }

  createIdentificationType(newIdentificationType: IdentificationType): Observable<IdentificationType> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    newIdentificationType.identificationTypeId = null;
    return this.httpClient.post<IdentificationType>(serviceUrl, newIdentificationType);
  }

  updateIdentificationType(identificationTypeId: number, updatedIdentificationType: IdentificationType): Observable<IdentificationType> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + identificationTypeId;
    updatedIdentificationType.identificationTypeId = identificationTypeId;
    return this.httpClient.post<IdentificationType>(serviceUrl, updatedIdentificationType);
  }

  deleteIdentificationType(identificationTypeId: number): Observable<IdentificationType> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + identificationTypeId;
    return this.httpClient.delete<IdentificationType>(serviceUrl);
  }
}
