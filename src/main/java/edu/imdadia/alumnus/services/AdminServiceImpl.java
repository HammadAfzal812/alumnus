package edu.imdadia.alumnus.services;

import edu.imdadia.alumnus.entity.AdminEntity;
import edu.imdadia.alumnus.repository.AdminRepo;
import edu.imdadia.alumnus.util.JavaFXUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

private final AdminRepo adminRepo;


    @Override
    public void save(AdminEntity admin) {
        adminRepo.save(admin);
    }

    @Override
    public List<AdminEntity> findAll() {
        return adminRepo.findAll();
    }


    @Override
    public void deleteByIdCardNumber(String idCard) {
           adminRepo.deleteByIdCardNumber(idCard);
    }

    @Override
    public void deleteByUserName(String userName) {
        adminRepo.deleteByUserName(userName);
    }


    @Override
    public void deleteAll() {
        adminRepo.deleteAll();
    }

    @Override
    public Optional<AdminEntity> findByIdCardNumber(String idNumber) {
        return adminRepo.findByIdCardNumber(idNumber);
    }

    @Override
    public Optional<AdminEntity> findByUserName(String name) {
        return adminRepo.findByUserName(name);
    }
}

