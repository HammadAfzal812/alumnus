package edu.imdadia.alumnus.services;

import edu.imdadia.alumnus.entity.AdminEntity;


import java.util.Optional;

public interface AdminService {
    void deleteByIdCardNumber(String idCard);

     void deleteByUserName(String userName);
     void deleteAll();
    Optional<AdminEntity> findByIdCardNumber(String idNumber);
    Optional<AdminEntity> findByUserName(String name);
    void save(AdminEntity adminEntity);
}

