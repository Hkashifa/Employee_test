package com.example.Employee.repo;

import com.example.Employee.entity.employeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepo extends JpaRepository<employeeEntity, Long>{

    employeeEntity findByEmailContaining(String email);
}
