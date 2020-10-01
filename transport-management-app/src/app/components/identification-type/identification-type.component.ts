import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { finalize } from 'rxjs/operators';

import { IdentificationType } from '../../models/api/identification-type';
import { IdentificationTypeService } from '../../providers/api/identification-type.service';

@Component({
  selector: 'app-identification-type',
  templateUrl: './identification-type.component.html',
  styles: [],
})
export class IdentificationTypeComponent implements OnInit {
  identificationTypeForm: FormGroup;

  loadingSubmit: boolean;
  errorSubmit: boolean;
  validSubmit: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private IdentificationTypeService: IdentificationTypeService
  ) {}

  ngOnInit(): void {
    this.identificationTypeForm = this.formBuilder.group({
      name: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
      abbreviation: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
      description: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
    });
  }

  get name(): AbstractControl {
    return this.identificationTypeForm.get('name');
  }

  get abbreviation(): AbstractControl {
    return this.identificationTypeForm.get('abbreviation');
  }

  get description(): AbstractControl {
    return this.identificationTypeForm.get('description');
  }

  submit(): void {
    this.loadingSubmit = true;
    this.validSubmit = false;
    this.errorSubmit = false;
    const newIdentificationType: IdentificationType = {
      identificationTypeId: null,
      name: this.name.value,
      abbreviation: this.abbreviation.value,
      description: this.description.value,
    };
    console.log(newIdentificationType);
    this.IdentificationTypeService.createIdentificationType(
      newIdentificationType
    )
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
    this.identificationTypeForm.reset();
  }
}
