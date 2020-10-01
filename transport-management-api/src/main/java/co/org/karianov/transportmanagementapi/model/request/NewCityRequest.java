package co.org.karianov.transportmanagementapi.model.request;

import co.org.karianov.transportmanagementapi.model.entity.DepartmentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCityRequest {

	private String name;
	private DepartmentEntity department;
	
}
