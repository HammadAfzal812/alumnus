package edu.imdadia.alumnus.services;

import edu.imdadia.alumnus.entity.AlumnusEntity;
import edu.imdadia.alumnus.repository.AlumnusRepo;

import java.util.List;
import java.util.Optional;

public class AlumnusServiceImpl implements AlumnusService {
    private AlumnusRepo alumnusRepo;

    @Override
    public List<AlumnusEntity> findAllByDistrict(String district) {
        return alumnusRepo.findAllByDistrict(district);
    }

    @Override
    public List<AlumnusEntity> findAllByType(String type) {
        return alumnusRepo.findAllByType(type);
    }

    @Override
    public AlumnusEntity save(AlumnusEntity alumnusEntity) {
        return alumnusRepo.save(alumnusEntity);
    }

    @Override
    public void delete(AlumnusEntity alumnusEntity) {
        alumnusRepo.delete(alumnusEntity);
    }

    @Override
    public Optional<AlumnusEntity> findByAlumnusName(String name) {
        return alumnusRepo.findByAlumnusName(name);
    }

    @Override
    public Optional<AlumnusEntity> findByAlumnusNameAndFatherName(String name, String fatherName) {
        return alumnusRepo.findByAlumnusNameAndFatherName(name,fatherName);
    }
}
