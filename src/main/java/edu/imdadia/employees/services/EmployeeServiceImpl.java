package edu.imdadia.employees.services;

import edu.imdadia.employees.entity.EmployeeEntity;
import edu.imdadia.employees.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    @Override
    public Optional<EmployeeEntity> findByEmployeeNameIgnoreCase(String name) {
        return employeeRepo.findByEmployeeNameIgnoreCase(name);
    }

    @Override
    public Optional<EmployeeEntity> findByEmployeeNameIgnoreCaseAndFatherNameIgnoreCase(String name, String fatherName) {
        return employeeRepo.findByEmployeeNameIgnoreCaseAndFatherNameIgnoreCase(name,fatherName);
    }

    @Override
    public Optional<EmployeeEntity> findByEmployeeId(Integer id) {
        return employeeRepo.findByEmployeeId(id);
    }

    @Override
    public Optional<EmployeeEntity> findByEmployeeNameIgnoreCaseAndPassword(String name, String password) {
        return employeeRepo.findByEmployeeNameIgnoreCaseAndPassword(name,password);
    }

    public void save(EmployeeEntity employee){
        employeeRepo.save(employee);
    }
    public void delete(EmployeeEntity employee){
        employeeRepo.delete(employee);
    }
    public Optional<EmployeeEntity> findByIdCardNumber(final Integer idNumber){
       return employeeRepo.findByIdCardNumber(idNumber);
    }

}
