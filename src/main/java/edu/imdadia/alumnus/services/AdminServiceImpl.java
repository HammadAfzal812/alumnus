package edu.imdadia.alumnus.services;

import edu.imdadia.alumnus.entity.AdminEntity;
import edu.imdadia.alumnus.repository.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

private final AdminRepo adminRepo;
    @Override
    public Optional<AdminEntity> findByAdminNameIgnoreCase(String name) {
        return adminRepo.findByAdminNameIgnoreCase(name);
    }

    @Override
    public Optional<AdminEntity> findByAdminNameIgnoreCaseAndFatherNameIgnoreCase(String name, String fatherName) {
        return adminRepo.findByAdminNameIgnoreCaseAndFatherNameIgnoreCase(name,fatherName);
    }

    @Override
    public Optional<AdminEntity> findByAdminId(Integer id) {
        return adminRepo.findByAdminId(id);
    }

    @Override
    public Optional<AdminEntity> findByAdminNameIgnoreCaseAndPassword(String name, String password) {
        return adminRepo.findByAdminNameIgnoreCaseAndPassword(name,password);
    }

    @Override
    public Optional<AdminEntity> findByIdCardNumber(String idNumber) {
        return adminRepo.findByIdCardNumber(idNumber);
    }

    @Override
    public Optional<AdminEntity> findByUserName(String name) {
        return adminRepo.findByUserName(name);
    }

    @Override
    public void deleteByAdminNameIgnoreCaseAndIdCardNumber(String name, Integer idCard) {
        adminRepo.deleteByAdminNameIgnoreCaseAndIdCardNumber(name,idCard);

    }


    @Override
    public void save(AdminEntity admin) {
        adminRepo.save(admin);
    }



    @Override
    public void deleteByUserName(String userName) {
        adminRepo.deleteByUserName(userName);
    }


    @Override
    public void deleteAll() {
        adminRepo.deleteAll();
    }


}

