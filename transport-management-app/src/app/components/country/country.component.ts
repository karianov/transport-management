import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { finalize } from 'rxjs/operators';

import { Country } from '../../models/api/country';
import { CountryService } from '../../providers/api/country.service';

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styles: [],
})
export class CountryComponent implements OnInit {
  countryForm: FormGroup;

  loadingSubmit: boolean;
  errorSubmit: boolean;
  validSubmit: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private countryService: CountryService
  ) {}

  ngOnInit(): void {
    this.countryForm = this.formBuilder.group({
      name: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
    });
  }

  get name(): AbstractControl {
    return this.countryForm.get('name');
  }

  submit(): void {
    this.loadingSubmit = true;
    this.validSubmit = false;
    this.errorSubmit = false;
    const newCountry: Country = {
      countryId: null,
      name: this.name.value,
    };
    this.countryService
      .createCountry(newCountry)
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
    this.countryForm.reset();
  }
}
