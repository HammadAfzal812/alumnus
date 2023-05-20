package edu.imdadia.employees.services;

import edu.imdadia.employees.entity.AdminEntity;
import edu.imdadia.employees.entity.AttendanceEntity;
import edu.imdadia.employees.entity.EmployeeEntity;
import edu.imdadia.employees.repository.AdminRepo;
import edu.imdadia.employees.repository.AttendanceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepo attendanceRepo;

    @Override
    public void save(AttendanceEntity attendanceEntity){
        attendanceRepo.save(attendanceEntity);
    }

    @Override
    public List<AttendanceEntity> getAllByMonthAndYear(Integer month, Integer year) {
        return attendanceRepo.getAllByMonthAndYear(month,year);
    }

    @Override
    public List<AttendanceEntity> getAllByMonthAndYearAfter(Integer month, Integer year) {
        return attendanceRepo.getAllByMonthAndYearAfter(month,year);
    }

    @Override
    public List<AttendanceEntity> getAllByWorkerId(Integer id) {
        return attendanceRepo.getAllByWorkerId(id);
    }


}

