package com.example.Employee.service.impl;

import com.example.Employee.dto.EmployeeDTO;
import com.example.Employee.entity.EmployeeEntity;
import com.example.Employee.repo.EmployeeRepo;
import com.example.Employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepo employeeRepository;

    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepo employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        return employeeRepository.findAll().stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createEmployees(EmployeeDTO employeeDTO) {

        EmployeeEntity employeeConverted = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeRepository.save(employeeConverted);

        EmployeeDTO employeeResponse = modelMapper.map(employeeConverted, EmployeeDTO.class);
        return employeeResponse;

    }

    @Override
    public EmployeeDTO updateEmployees(Long id, EmployeeDTO employeeDTO) {

        Optional<EmployeeEntity> updated_employee = employeeRepository.findById(id);

        if (updated_employee.isPresent()) {
            EmployeeEntity EmployeeEntity = updated_employee.get();

            EmployeeEntity.setFirstName(employeeDTO.getFirstName());
            EmployeeEntity.setLastName(employeeDTO.getLastName());
            EmployeeEntity.setGender(employeeDTO.getGender());
            EmployeeEntity.setEmail(employeeDTO.getEmail());
            EmployeeEntity.setAge(employeeDTO.getAge());
            EmployeeEntity.setAddress(employeeDTO.getAddress());
            EmployeeEntity.setContactNumber(employeeDTO.getContactNumber());

            employeeRepository.save(EmployeeEntity);//
            // entity to DTO
            EmployeeDTO employeeResponse = modelMapper.map(EmployeeEntity, EmployeeDTO.class);
            return employeeResponse;
        }
        return null;
    }

    @Override
    public void deleteEmployees(long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        EmployeeEntity employee = optionalEmployee
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));

        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDTO getEmployeesById(long id) {

        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        EmployeeEntity employee = optionalEmployee
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getEmployeeByAttribute(String email,String number,String gender) {
        //List<EmployeeEntity> employee = employeeRepository.findByEmailOrContactNumberOrFirstName(email,number,firstName);
        //System.out.println(email);
        if(email == null && number == null)
        {
            return employeeRepository.findByGenderEquals(gender).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                    .collect(Collectors.toList());
        }
        else if(gender == null && number == null)
        {
            return employeeRepository.findByEmailEquals(email).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                    .collect(Collectors.toList());
        }
        else if(gender == null && email == null)
        {
            return employeeRepository.findByContactNumberEquals(number).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                    .collect(Collectors.toList());
        }
        else if(email == null )
        {
            return employeeRepository.findByGenderEqualsAndContactNumberEquals(gender,number).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                    .collect(Collectors.toList());
        }
        else if(gender == null )
        {
            return employeeRepository.findByEmailEqualsAndContactNumberEquals(email,number).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                    .collect(Collectors.toList());
        }
        else if(number == null )
        {
            return employeeRepository.findByEmailEqualsAndGenderEquals(email,gender).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
        }
        else {
            return employeeRepository.findByEmailEqualsAndContactNumberEqualsAndGenderEquals(email, number, gender).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                    .collect(Collectors.toList());
        }
    }
}


