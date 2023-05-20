package edu.imdadia.employees.entity;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(schema ="ems", name = "employees")
public class EmployeeEntity {

    @Id
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "employee_Name")
    private String employeeName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "idCard_number")
    private String idCardNumber;

    @Column(name = "address")
    private String address;

     @Column(name = "salary")
    private Double salary;

     @Column(name = "password")
    private String password;


}
