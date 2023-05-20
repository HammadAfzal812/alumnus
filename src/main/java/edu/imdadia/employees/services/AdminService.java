package edu.imdadia.employees.services;

import edu.imdadia.employees.entity.AdminEntity;
import edu.imdadia.employees.entity.EmployeeEntity;

import java.awt.*;
import java.util.Optional;

public interface AdminService {

        Optional<AdminEntity> findByAdminNameIgnoreCase(String Name);
        Optional<AdminEntity> findByAdminNameIgnoreCaseAndFatherNameIgnoreCase(String name, String fatherName);
        Optional<AdminEntity> findByAdminId(Integer id);
        Optional<AdminEntity> findByAdminNameIgnoreCaseAndPassword(String name, String password);
        Optional<AdminEntity> findByIdCardNumber(Integer idNumber);
     public void deleteByAdminNameIgnoreCaseAndIdCardNumber(String name,String idCard);
     public void deleteById(Integer id);
     public void deleteAll();
   void save(AdminEntity adminEntity);
}

