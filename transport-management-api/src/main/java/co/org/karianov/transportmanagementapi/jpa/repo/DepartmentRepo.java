package co.org.karianov.transportmanagementapi.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.org.karianov.transportmanagementapi.model.entity.DepartmentEntity;

public interface DepartmentRepo extends JpaRepository<DepartmentEntity, Integer> {

	public DepartmentEntity findByDepartmentId(Integer departmentId);

}
