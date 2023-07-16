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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class employeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(employeeServiceImpl.class);

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

    @Override
    public EmployeeDTO getEmployeeByEmail(String email) {

        logger.info("This is the email:."+email);
        employeeEntity employee =  employeeRepository.findByEmailContaining(email);
        //System.out.println(email);
        return modelMapper.map(employee,EmployeeDTO.class);

    }
    }


