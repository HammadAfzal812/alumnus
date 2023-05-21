package edu.imdadia.alumnus.services;

import edu.imdadia.alumnus.entity.AdminEntity;

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

