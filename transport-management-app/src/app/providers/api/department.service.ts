import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Department } from '../../models/api/department';
import { API_BASE_LOCATION } from '../../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class DepartmentService {
  private endpoint: string;

  constructor(private httpClient: HttpClient) {
    this.endpoint = 'department';
  }

  getDepartments(): Observable<Department[]> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    return this.httpClient.get<Department[]>(serviceUrl);
  }

  getDepartment(departmentId: number): Observable<Department> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + departmentId;
    return this.httpClient.get<Department>(serviceUrl);
  }

  createDepartment(newDepartment: Department): Observable<Department> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint;
    newDepartment.departmentId = null;
    return this.httpClient.post<Department>(serviceUrl, newDepartment);
  }

  updateDepartment(departmentId: number, updatedDepartment: Department): Observable<Department> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + departmentId;
    updatedDepartment.departmentId = departmentId;
    return this.httpClient.post<Department>(serviceUrl, updatedDepartment);
  }

  deleteDepartment(departmentId: number): Observable<Department> {
    const serviceUrl = API_BASE_LOCATION + this.endpoint + '/' + departmentId;
    return this.httpClient.delete<Department>(serviceUrl);
  }
}
