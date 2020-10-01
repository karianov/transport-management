package co.org.karianov.transportmanagementapi.model.request;

import co.org.karianov.transportmanagementapi.model.entity.CityEntity;
import co.org.karianov.transportmanagementapi.model.entity.IdentificationTypeEntity;
import co.org.karianov.transportmanagementapi.model.entity.PersonEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCompanyRequest {

	private String identificationNumber;
	private String fullName;
	private String address;
	private String phoneNumber;
	private IdentificationTypeEntity identificationType;
	private CityEntity city;
	private PersonEntity legalRepresentative;
	
}
