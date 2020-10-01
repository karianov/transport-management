package co.org.karianov.transportmanagementapi.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCompanyRequest {

	private String identificationNumber;
	private String fullName;
	private String address;
	private String phoneNumber;
	private Integer identificationTypeId;
	private Integer cityId;
	private Integer legalRepresentativeId;
	
}
