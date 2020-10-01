import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Company } from '../../models/api/company';
import { API_BASE_LOCATION } from '../../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  private endpoint: string;

  constructor(private httpClient: HttpClient) {
    this.endpoint = 'company';
  }

  getCompanies(): Observable<Company[]> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    return this.httpClient.get<Company[]>(serviceUrl);
  }

  getCompany(companyId: number): Observable<Company> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + companyId;
    return this.httpClient.get<Company>(serviceUrl);
  }

  createCompany(newCompany: Company): Observable<Company> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    newCompany.companyId = null;
    return this.httpClient.post<Company>(serviceUrl, newCompany);
  }

  updateCompany(companyId: number, updatedCompany: Company): Observable<Company> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + companyId;
    updatedCompany.companyId = companyId;
    return this.httpClient.post<Company>(serviceUrl, updatedCompany);
  }

  deleteCompany(companyId: number): Observable<Company> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + companyId;
    return this.httpClient.delete<Company>(serviceUrl);
  }
}
