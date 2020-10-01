package co.org.karianov.transportmanagementapi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "identification_type")
@Getter
@Setter
public class IdentificationTypeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "identification_type_id")
	private Integer identificationTypeId;

	@Column(name = "name", length = 35, nullable = false)
	private String name;

	@Column(name = "abbreviation", length = 4, nullable = false)
	private String abbreviation;
	
	@Column(name = "description", length = 255, nullable = true)
	private String description;
	
}
