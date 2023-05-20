package edu.imdadia.employees.services;

import edu.imdadia.employees.entity.AdminEntity;
import edu.imdadia.employees.entity.EmployeeEntity;

import java.util.Optional;

public interface EmployeeService {
    Optional<EmployeeEntity> findByEmployeeNameIgnoreCase(String Name);
    Optional<EmployeeEntity> findByEmployeeNameIgnoreCaseAndFatherNameIgnoreCase(String name, String fatherName);
    Optional<EmployeeEntity> findByEmployeeId(Integer id);
    Optional<EmployeeEntity> findByEmployeeNameIgnoreCaseAndPassword(String name, String password);
    Optional<EmployeeEntity> findByIdCardNumber(Integer idNumber);

   public void save(EmployeeEntity employee);
}
