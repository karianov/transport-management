import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CityComponent } from './city/city.component';
import { CompanyComponent } from './company/company.component';
import { CountryComponent } from './country/country.component';
import { DepartmentComponent } from './department/department.component';
import { IdentificationTypeComponent } from './identification-type/identification-type.component';
import { PersonComponent } from './person/person.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    CityComponent,
    CompanyComponent,
    CountryComponent,
    DepartmentComponent,
    IdentificationTypeComponent,
    PersonComponent,
  ],
  imports: [CommonModule, ReactiveFormsModule],
  exports: [
    CityComponent,
    CompanyComponent,
    CountryComponent,
    DepartmentComponent,
    IdentificationTypeComponent,
    PersonComponent,
  ],
})
export class ComponentsModule {}
