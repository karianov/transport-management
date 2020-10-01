import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { finalize } from 'rxjs/operators';

import { Company } from 'src/app/models/api/company';
import { CompanyService } from '../../providers/api/company.service';
import { Person } from 'src/app/models/api/person';
import { PersonService } from '../../providers/api/person.service';
import { City } from '../../models/api/city';
import { CityService } from '../../providers/api/city.service';
import { IdentificationType } from '../../models/api/identification-type';
import { IdentificationTypeService } from '../../providers/api/identification-type.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styles: [],
})
export class CompanyComponent implements OnInit {
  companyForm: FormGroup;

  loadingPrevious: boolean;
  errorPrevious: boolean;
  validPrevious: boolean;

  loadingSubmit: boolean;
  errorSubmit: boolean;
  validSubmit: boolean;

  cities: City[];
  identificationTypes: IdentificationType[];
  legalRepresentatives: Person[];

  constructor(
    private formBuilder: FormBuilder,
    private companyService: CompanyService,
    private personService: PersonService,
    private cityService: CityService,
    private identificationTypeService: IdentificationTypeService
  ) {}

  ngOnInit(): void {
    this.reload();
    this.companyForm = this.formBuilder.group({
      identificationNumber: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
      fullName: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
      address: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
      phoneNumber: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
      identificationType: new FormControl(null, [Validators.required]),
      city: new FormControl(null, [Validators.required]),
      legalRepresentative: new FormControl(null, [Validators.required]),
    });
  }

  reload(): void {
    this.loadIdentificationTypes();
    this.loadCities();
    this.loadPeople();
  }

  private loadIdentificationTypes() {
    this.loadingPrevious = true;
    this.validPrevious = false;
    this.errorPrevious = false;
    this.identificationTypeService
      .getIdentificationTypes()
      .pipe(finalize(() => (this.loadingPrevious = false)))
      .subscribe(
        (response) => {
          if (response) {
            this.validPrevious = true;
            this.identificationTypes = response;
          } else {
            this.errorPrevious = true;
          }
        },
        () => {
          this.errorPrevious = true;
        }
      );
  }

  private loadCities() {
    this.loadingPrevious = true;
    this.validPrevious = false;
    this.errorPrevious = false;
    this.cityService
      .getCities()
      .pipe(finalize(() => (this.loadingPrevious = false)))
      .subscribe(
        (response) => {
          if (response) {
            this.validPrevious = true;
            this.cities = response;
          } else {
            this.errorPrevious = true;
          }
        },
        () => {
          this.errorPrevious = true;
        }
      );
  }

  private loadPeople() {
    this.loadingPrevious = true;
    this.validPrevious = false;
    this.errorPrevious = false;
    this.personService
      .getPeople()
      .pipe(finalize(() => (this.loadingPrevious = false)))
      .subscribe(
        (response) => {
          if (response) {
            this.validPrevious = true;
            this.legalRepresentatives = response;
          } else {
            this.errorPrevious = true;
          }
        },
        () => {
          this.errorPrevious = true;
        }
      );
  }

  get identificationNumber(): AbstractControl {
    return this.companyForm.get('identificationNumber');
  }

  get fullName(): AbstractControl {
    return this.companyForm.get('fullName');
  }

  get address(): AbstractControl {
    return this.companyForm.get('address');
  }

  get phoneNumber(): AbstractControl {
    return this.companyForm.get('phoneNumber');
  }

  get identificationType(): AbstractControl {
    return this.companyForm.get('identificationType');
  }

  get city(): AbstractControl {
    return this.companyForm.get('city');
  }

  get legalRepresentative(): AbstractControl {
    return this.companyForm.get('legalRepresentative');
  }

  submit(): void {
    this.loadingSubmit = true;
    this.validSubmit = false;
    this.errorSubmit = false;
    const newCompany: Company = {
      companyId: null,
      identificationNumber: this.identificationNumber.value,
      fullName: this.fullName.value,
      address: this.address.value,
      phoneNumber: this.phoneNumber.value,
      identificationType: this.identificationType.value,
      city: this.city.value,
      legalRepresentative: this.legalRepresentative.value,
    };
    this.companyService
      .createCompany(newCompany)
      .pipe(finalize(() => (this.loadingSubmit = false)))
      .subscribe(
        (response) => {
          if (response) {
            this.validSubmit = true;
          } else {
            this.errorSubmit = true;
          }
        },
        () => {
          this.errorSubmit = true;
        }
      );
    this.companyForm.reset();
  }
}
