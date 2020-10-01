package co.org.karianov.transportmanagementapi.model.request;

import co.org.karianov.transportmanagementapi.model.entity.CountryEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewDepartmentRequest {

	private String name;
	private CountryEntity country;
	
}
