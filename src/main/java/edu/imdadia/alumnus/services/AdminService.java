package edu.imdadia.alumnus.services;

import edu.imdadia.alumnus.entity.AdminEntity;


import java.util.Optional;

public interface AdminService {

        Optional<AdminEntity> findByAdminNameIgnoreCase(String Name);
        Optional<AdminEntity> findByAdminNameIgnoreCaseAndFatherNameIgnoreCase(String name, String fatherName);
        Optional<AdminEntity> findByAdminId(Integer id);
        Optional<AdminEntity> findByAdminNameIgnoreCaseAndPassword(String name, String password);

     void deleteByUserName(String userName);
     void deleteAll();
    Optional<AdminEntity> findByIdCardNumber(String idNumber);
    Optional<AdminEntity> findByUserName(String name);
    void save(AdminEntity adminEntity);
}

