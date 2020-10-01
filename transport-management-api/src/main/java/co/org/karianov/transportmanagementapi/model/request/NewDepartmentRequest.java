package co.org.karianov.transportmanagementapi.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewDepartmentRequest {

	private String name;
	private Integer countryId;
	
}
