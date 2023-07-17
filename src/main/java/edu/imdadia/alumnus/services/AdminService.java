package edu.imdadia.alumnus.services;

import edu.imdadia.alumnus.entity.AdminEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public interface AdminService {
    void deleteByIdCardNumber(String idCard);

     void deleteByUserName(String userName);
     void deleteAll();
    Optional<AdminEntity> findByIdCardNumber(String idNumber);
    Optional<AdminEntity> findByUserName(String name);
    void save(AdminEntity adminEntity);
    List<AdminEntity>findAll();
}

