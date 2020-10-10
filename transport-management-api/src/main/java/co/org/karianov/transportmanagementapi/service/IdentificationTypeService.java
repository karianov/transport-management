package co.org.karianov.transportmanagementapi.service;

import java.util.List;

import co.org.karianov.transportmanagementapi.model.entity.IdentificationTypeEntity;
import co.org.karianov.transportmanagementapi.model.request.NewIdentificationTypeRequest;

public interface IdentificationTypeService {

	public IdentificationTypeEntity findIdentificationTypeById(Integer identificationTypeId);

	public List<IdentificationTypeEntity> findAllIdentificationTypes();

	public IdentificationTypeEntity saveIdentificationType(NewIdentificationTypeRequest newIdentificationTypeRequest);

	public IdentificationTypeEntity updateIdentificationType(Integer identificationTypeId, IdentificationTypeEntity identificationTypeToUpdate);

	public IdentificationTypeEntity deleteIdentificationType(Integer identificationTypeId);
	
}
