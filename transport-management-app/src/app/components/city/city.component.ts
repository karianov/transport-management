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
import { City } from '../../models/api/city';
import { DepartmentService } from '../../providers/api/department.service';
import { CityService } from '../../providers/api/city.service';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styles: [],
})
export class CityComponent implements OnInit {
  cityForm: FormGroup;

  loadingPrevious: boolean;
  errorPrevious: boolean;
  validPrevious: boolean;

  loadingSubmit: boolean;
  errorSubmit: boolean;
  validSubmit: boolean;

  departments: Department[];

  constructor(
    private formBuilder: FormBuilder,
    private departmentService: DepartmentService,
    private cityService: CityService
  ) {
    this.departments = [];
  }

  ngOnInit(): void {
    this.loadDepartments();
    this.cityForm = this.formBuilder.group({
      name: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
      department: new FormControl(null, [Validators.required]),
    });
  }

  reload(): void {
    this.loadDepartments();
  }

  private loadDepartments() {
    this.loadingPrevious = true;
    this.validPrevious = false;
    this.errorPrevious = false;
    this.departmentService
      .getDepartments()
      .pipe(finalize(() => (this.loadingPrevious = false)))
      .subscribe(
        (response) => {
          if (response) {
            this.validPrevious = true;
            this.departments = response;
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
    return this.cityForm.get('name');
  }

  get department(): AbstractControl {
    return this.cityForm.get('department');
  }

  submit(): void {
    this.loadingSubmit = true;
    this.validSubmit = false;
    this.errorSubmit = false;
    const newCity: City = {
      cityId: null,
      name: this.name.value,
      department: this.department.value,
    };
    this.cityService
      .createCity(newCity)
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
    this.cityForm.reset();
  }
}
