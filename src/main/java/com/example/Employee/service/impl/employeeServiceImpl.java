package com.example.Employee.service.impl;

import com.example.Employee.dto.EmployeeDTO;
import com.example.Employee.entity.employeeEntity;
import com.example.Employee.repo.employeeRepo;
import com.example.Employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class employeeServiceImpl implements EmployeeService {

    private final ModelMapper modelMapper;
    private final employeeRepo employeeRepository;

    public employeeServiceImpl(ModelMapper modelMapper, employeeRepo employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<employeeEntity> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public EmployeeDTO createEmployees(EmployeeDTO employeeDTO) {

        employeeEntity employeeConverted = modelMapper.map(employeeDTO,  employeeEntity.class);
       employeeRepository.save(employeeConverted);

        EmployeeDTO employeeResponse = modelMapper.map(employeeConverted,   EmployeeDTO.class);
        return employeeResponse;

    }

    @Override
    public EmployeeDTO updateEmployees(Long id, EmployeeDTO employeeDTO) {

        Optional<employeeEntity> updated_employee = employeeRepository.findById(id);

        if ( updated_employee.isPresent()) {
            employeeEntity EmployeeEntity =  updated_employee.get();

            EmployeeEntity.setFirstName(employeeDTO.getFirstName());
            EmployeeEntity.setLastName(employeeDTO.getLastName());
            EmployeeEntity.setGender(employeeDTO.getGender());
            EmployeeEntity.setEmail(employeeDTO.getEmail());
            EmployeeEntity.setAge(employeeDTO.getAge());
            EmployeeEntity.setAddress(employeeDTO.getAddress());
            EmployeeEntity.setContactNumber(employeeDTO.getContactNumber());

            employeeRepository.save(EmployeeEntity);//
            // entity to DTO
            EmployeeDTO employeeResponse = modelMapper.map(EmployeeEntity,EmployeeDTO.class);
            return employeeResponse;
        }
        return null;
    }

    @Override
    public void deleteEmployees(long id)
    {
        Optional<employeeEntity> optionalEmployee = employeeRepository.findById(id);
        employeeEntity employee = optionalEmployee
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));

        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDTO getEmployeesById(long id) {

        Optional<employeeEntity> optionalEmployee = employeeRepository.findById(id);
        employeeEntity employee = optionalEmployee
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public List<employeeEntity> getEmployeeByEmailandNumber(String email, String number) {

        Specification<employeeEntity> spec = Specification.where(null);

        if (email != null && !email.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("email"), "%" + email + "%"));
        }

        if (number != null && !number.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("number"), number));
        }

        return employeeRepository.findAll(spec);

    }
    }


