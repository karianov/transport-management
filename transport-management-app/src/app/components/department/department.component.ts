import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { finalize } from 'rxjs/operators';

import { Department } from 'src/app/models/api/department';
import { Country } from '../../models/api/country';
import { CountryService } from '../../providers/api/country.service';
import { DepartmentService } from '../../providers/api/department.service';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styles: [],
})
export class DepartmentComponent implements OnInit {
  departmentForm: FormGroup;

  loadingPrevious: boolean;
  errorPrevious: boolean;
  validPrevious: boolean;

  loadingSubmit: boolean;
  errorSubmit: boolean;
  validSubmit: boolean;

  countries: Country[];

  constructor(
    private formBuilder: FormBuilder,
    private countryService: CountryService,
    private departmentService: DepartmentService
  ) {}

  ngOnInit(): void {
    this.loadCountries();
    this.departmentForm = this.formBuilder.group({
      name: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
      country: new FormControl(null, [Validators.required]),
    });
  }

  reload(): void {
    this.loadCountries();
  }

  private loadCountries() {
    this.loadingPrevious = true;
    this.validPrevious = false;
    this.errorPrevious = false;
    this.countryService
      .getCountries()
      .pipe(finalize(() => (this.loadingPrevious = false)))
      .subscribe(
        (response) => {
          if (response) {
            this.validPrevious = true;
            this.countries = response;
          } else {
            this.errorPrevious = true;
          }
        },
        () => {
          this.errorPrevious = true;
        }
      );
  }

  get name(): AbstractControl {
    return this.departmentForm.get('name');
  }

  get country(): AbstractControl {
    return this.departmentForm.get('country');
  }

  submit(): void {
    this.loadingSubmit = true;
    this.validSubmit = false;
    this.errorSubmit = false;
    const newDepartment: Department = {
      departmentId: null,
      name: this.name.value,
      country: this.country.value,
    };
    this.departmentService
      .createDepartment(newDepartment)
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
    this.departmentForm.reset();
  }
}
