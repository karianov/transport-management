package co.org.karianov.transportmanagementapi.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewIdentificationTypeRequest {

	private String name;
	private String abbreviation;
	private String description;
	
}
