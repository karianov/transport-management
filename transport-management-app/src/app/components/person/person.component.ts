import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { finalize } from 'rxjs/operators';

import { Person } from 'src/app/models/api/person';
import { PersonService } from '../../providers/api/person.service';
import { City } from '../../models/api/city';
import { CityService } from '../../providers/api/city.service';
import { IdentificationType } from '../../models/api/identification-type';
import { IdentificationTypeService } from '../../providers/api/identification-type.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styles: [],
})
export class PersonComponent implements OnInit {
  personForm: FormGroup;

  loadingPrevious: boolean;
  errorPrevious: boolean;
  validPrevious: boolean;

  loadingSubmit: boolean;
  errorSubmit: boolean;
  validSubmit: boolean;

  cities: City[];
  identificationTypes: IdentificationType[];

  constructor(
    private formBuilder: FormBuilder,
    private personService: PersonService,
    private cityService: CityService,
    private identificationTypeService: IdentificationTypeService
  ) {}

  ngOnInit(): void {
    this.reload();
    this.personForm = this.formBuilder.group({
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
    });
  }

  reload(): void {
    this.loadIdentificationTypes();
    this.loadCities();
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

  get identificationNumber(): AbstractControl {
    return this.personForm.get('identificationNumber');
  }

  get fullName(): AbstractControl {
    return this.personForm.get('fullName');
  }

  get address(): AbstractControl {
    return this.personForm.get('address');
  }

  get phoneNumber(): AbstractControl {
    return this.personForm.get('phoneNumber');
  }

  get identificationType(): AbstractControl {
    return this.personForm.get('identificationType');
  }

  get city(): AbstractControl {
    return this.personForm.get('city');
  }

  submit(): void {
    this.loadingSubmit = true;
    this.validSubmit = false;
    this.errorSubmit = false;
    const newPerson: Person = {
      personId: null,
      identificationNumber: this.identificationNumber.value,
      fullName: this.fullName.value,
      address: this.address.value,
      phoneNumber: this.phoneNumber.value,
      identificationType: this.identificationType.value,
      city: this.city.value
    };
    this.personService
      .createPerson(newPerson)
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
    this.personForm.reset();
  }
}
