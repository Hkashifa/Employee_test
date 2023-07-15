package com.example.Employee.repo;

import com.example.Employee.entity.employeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface employeeRepo extends JpaRepository<employeeEntity, Long>, JpaSpecificationExecutor<employeeEntity> {

    employeeEntity findByEmailContaining(String email);
}
