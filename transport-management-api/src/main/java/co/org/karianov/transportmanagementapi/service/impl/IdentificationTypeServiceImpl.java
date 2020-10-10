package co.org.karianov.transportmanagementapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.org.karianov.transportmanagementapi.jpa.repo.IdentificationTypeRepo;
import co.org.karianov.transportmanagementapi.model.entity.IdentificationTypeEntity;
import co.org.karianov.transportmanagementapi.model.request.NewIdentificationTypeRequest;
import co.org.karianov.transportmanagementapi.service.IdentificationTypeService;
import co.org.karianov.transportmanagementapi.service.MapperService;

@Service(value = "identificationTypeService")
public class IdentificationTypeServiceImpl implements IdentificationTypeService {

	@Autowired
	private IdentificationTypeRepo identificationTypeRepo;

	@Autowired
	private MapperService mapperService;

	@Override
	public IdentificationTypeEntity findIdentificationTypeById(Integer identificationTypeId) {
		IdentificationTypeEntity searchedIdentificationType = identificationTypeRepo
				.findByIdentificationTypeId(identificationTypeId);
		if (searchedIdentificationType != null) {
			return searchedIdentificationType;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Identification Type " + identificationTypeId + " does not exist.");
		}
	}

	@Override
	public List<IdentificationTypeEntity> findAllIdentificationTypes() {
		return identificationTypeRepo.findAll();
	}

	@Override
	public IdentificationTypeEntity saveIdentificationType(NewIdentificationTypeRequest newIdentificationTypeRequest) {
		try {
			IdentificationTypeEntity identificationTypeToCreate = mapperService.map(newIdentificationTypeRequest,
					IdentificationTypeEntity.class);
			return identificationTypeRepo.save(identificationTypeToCreate);
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Identification Type was not able to save due to input values.");
		}
	}

	@Override
	public IdentificationTypeEntity updateIdentificationType(Integer identificationTypeId,
			IdentificationTypeEntity identificationTypeToUpdate) {
		findIdentificationTypeById(identificationTypeId);
		identificationTypeToUpdate.setIdentificationTypeId(identificationTypeId);
		try {
			return identificationTypeRepo.save(identificationTypeToUpdate);
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"Identification Type was not able to update due to existing values.");
		}
	}

	@Override
	public IdentificationTypeEntity deleteIdentificationType(Integer identificationTypeId) {
		IdentificationTypeEntity deletedIdentificationType = findIdentificationTypeById(identificationTypeId);
		try {
			identificationTypeRepo.deleteById(identificationTypeId);
			return deletedIdentificationType;
		} catch (Exception exception) {
			System.out.println(exception);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Identification Type " + identificationTypeId + " was not able to delete.");
		}
	}

}
