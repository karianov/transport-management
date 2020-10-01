import { City } from './city';
import { IdentificationType } from './identification-type';

export interface Person {
  personId: number;
  identificationNumber: string;
  fullName: string;
  address: string;
  phoneNumber: string;
  identificationType: IdentificationType;
  city: City;
}
