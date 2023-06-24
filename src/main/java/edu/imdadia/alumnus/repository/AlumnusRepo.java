package edu.imdadia.alumnus.repository;
import edu.imdadia.alumnus.entity.AlumnusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnusRepo extends JpaRepository<AlumnusEntity, Integer> {

    Optional<AlumnusEntity> findByIdCardNumber(String Card);

    void deleteByIdCardNumber(String Card);

    List<AlumnusEntity> findByDistrict(String district);

    List<AlumnusEntity> findByType(String type);

    List<AlumnusEntity> findByGraduationYear(String type);


    List<AlumnusEntity> findByGraduationYearAndDistrictAndType(String GraduationYear,String District ,String Type);

}
