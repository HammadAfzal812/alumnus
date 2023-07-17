package edu.imdadia.alumnus.services;
import edu.imdadia.alumnus.entity.AlumnusEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AlumnusService {
    List<AlumnusEntity> findAllByDistrict(String district);

    List<AlumnusEntity> findAllByType(String type);
     void save(AlumnusEntity alumnusEntity);
    List<AlumnusEntity> findByGraduationYear(String year);

    List<AlumnusEntity> findByGraduationYearAndDistrictAndType(String GraduationYear,String District ,String Type);

    void deleteByAlumnusIdCardNumber(String idCard);
    AlumnusEntity findByIdCardNumber(String idCard);

    void deleteAll();
    List<AlumnusEntity>findAll();


}
