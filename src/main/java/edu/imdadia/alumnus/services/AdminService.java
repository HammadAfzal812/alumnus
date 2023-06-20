package edu.imdadia.alumnus.services;

import edu.imdadia.alumnus.entity.AdminEntity;

import java.util.Optional;

public interface AdminService {

        Optional<AdminEntity> findByAdminNameIgnoreCase(String Name);
        Optional<AdminEntity> findByAdminNameIgnoreCaseAndFatherNameIgnoreCase(String name, String fatherName);
        Optional<AdminEntity> findByAdminId(Integer id);
        Optional<AdminEntity> findByAdminNameIgnoreCaseAndPassword(String name, String password);
        Optional<AdminEntity> findByIdCardNumber(Integer idNumber);
    Optional<AdminEntity> findByUserName(String name);
     public void deleteByAdminNameIgnoreCaseAndIdCardNumber(String name,Integer idCard);

     public void deleteByUserName(String userName);
     public void deleteAll();

    void save(AdminEntity adminEntity);
}

