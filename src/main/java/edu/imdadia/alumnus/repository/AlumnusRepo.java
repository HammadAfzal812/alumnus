package edu.imdadia.alumnus.repository;

import edu.imdadia.alumnus.entity.AlumnusEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlumnusRepo extends JpaRepository<AlumnusEntity, Integer> {
    Optional<AlumnusEntity> findByAlumnusName(String name);
    Optional<AlumnusEntity>findByAlumnusNameAndFatherName(String name,String fatherName);

    List<AlumnusEntity> findAllByDistrict(String district);
    List<AlumnusEntity> findAllByType(String type);

}
