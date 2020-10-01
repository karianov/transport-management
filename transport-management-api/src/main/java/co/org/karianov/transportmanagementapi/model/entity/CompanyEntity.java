package co.org.karianov.transportmanagementapi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "company")
@Getter
@Setter
public class CompanyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Integer companyId;

	@Column(name = "identification_number", length = 30, nullable = false)
	private String identificationNumber;
	
	@Column(name = "full_name", length = 70, nullable = false)
	private String fullName;

	@Column(name = "address", length = 70, nullable = false)
	private String address;

	@Column(name = "phone_number", length = 30, nullable = false)
	private String phoneNumber;

	@ManyToOne
	@JoinColumn(name = "fk_identification_type_id", nullable = false)
	private IdentificationTypeEntity identificationType;

	@ManyToOne
	@JoinColumn(name = "fk_city_id", nullable = false)
	private CityEntity city;

	@ManyToOne
	@JoinColumn(name = "fk_legal_representative", nullable = false)
	private PersonEntity legalRepresentative;
	
}
