package co.org.karianov.transportmanagementapi.service;

import java.util.List;

import co.org.karianov.transportmanagementapi.model.entity.DepartmentEntity;
import co.org.karianov.transportmanagementapi.model.request.NewDepartmentRequest;

public interface DepartmentService {

	public DepartmentEntity findDepartmentById(Integer departmentId);

	public List<DepartmentEntity> findAllDepartments();

	public DepartmentEntity saveDepartment(NewDepartmentRequest newDepartmentRequest);

	public DepartmentEntity updateDepartment(Integer departmentId, DepartmentEntity departmentToUpdate);

	public DepartmentEntity deleteDepartment(Integer departmentId);

}
