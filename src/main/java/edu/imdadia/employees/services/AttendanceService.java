package edu.imdadia.employees.services;

import edu.imdadia.employees.entity.AdminEntity;
import edu.imdadia.employees.entity.AttendanceEntity;
import edu.imdadia.employees.repository.AttendanceRepo;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {

   void save(AttendanceEntity attendanceEntity);
   List<AttendanceEntity> getAllByMonthAndYear(Integer month, Integer year);

   List<AttendanceEntity>getAllByMonthAndYearAfter(Integer month,Integer year);


   List<AttendanceEntity>getAllByWorkerId(Integer id);

}

