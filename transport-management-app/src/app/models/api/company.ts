import { City } from "./city";
import { IdentificationType } from "./identification-type";
import { Person } from "./person";

export interface Company {
  companyId: number;
  identificationNumber: string;
  fullName: string;
  address: string;
  phoneNumber: string;
  identificationType: IdentificationType;
  city: City;
  legalRepresentative: Person;
}
