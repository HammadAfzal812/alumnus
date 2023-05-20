package edu.imdadia.employees.repository;

import edu.imdadia.employees.entity.AttendanceEntity;
import edu.imdadia.employees.entity.EmployeeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<AttendanceEntity, Integer> {

    List<AttendanceEntity>getAllByMonthAndYear(Integer month,Integer year);

    List<AttendanceEntity>getAllByMonthAndYearAfter(Integer month,Integer year);


    List<AttendanceEntity>getAllByWorkerId(Integer id);

}
