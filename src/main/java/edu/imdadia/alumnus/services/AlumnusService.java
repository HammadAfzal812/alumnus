package edu.imdadia.alumnus.services;

import edu.imdadia.alumnus.entity.AlumnusEntity;

import java.util.List;
import java.util.Optional;

public interface AlumnusService {
    List<AlumnusEntity> findAllByDistrict(String district);

    List<AlumnusEntity> findAllByType(String type);
    public void save(AlumnusEntity alumnusEntity);
    public void delete(AlumnusEntity alumnusEntity);

    Optional<AlumnusEntity> findByAlumnusName(String name);
    Optional<AlumnusEntity>findByAlumnusNameAndFatherName(String name,String fatherName);


    List<AlumnusEntity> findByType(String type);

    List<AlumnusEntity> findByGraduationYear(String year);

    List<AlumnusEntity> findByGraduationYearAndDistrictAndType(String GraduationYear,String District ,String Type);

    void deleteByAlumnusIdCardNumber(String idCard);
    AlumnusEntity findByIdCardNumber(String idCard);


}
