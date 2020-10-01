package co.org.karianov.transportmanagementapi.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCityRequest {

	private String name;
	private Integer departmentId;
	
}
