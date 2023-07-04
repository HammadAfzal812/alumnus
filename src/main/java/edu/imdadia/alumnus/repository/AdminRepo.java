package edu.imdadia.alumnus.repository;

import edu.imdadia.alumnus.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AdminRepo extends JpaRepository<AdminEntity, Integer> {
        List<AdminEntity> findAll();
        AdminEntity deleteByUserName(String userName);
        Optional<AdminEntity> deleteByIdCardNumber(String idCard);
        Optional<AdminEntity> findByIdCardNumber(String idNumber);
        Optional<AdminEntity>findByUserName(String name);
}
