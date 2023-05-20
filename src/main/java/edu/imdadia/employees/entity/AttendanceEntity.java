package edu.imdadia.employees.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(schema = "ems", name = "attendance")
public class AttendanceEntity {
    @Id
    @Column(name = "worker_id")
    private Integer workerId;

    @Column(name = "worker_Name")
    private String workerName;


    @Column(name = "worker_type")
    private String workerType;

    @Column(name = "attendance_condition")
    private String attendanceCondition;

    @Column(name = "Month")
    private Integer month;

    @Column(name = "Year")
    private Integer year;

    @Column(name = "Day")
    private Integer day;
}
