package edu.imdadia.alumnus.entity;

import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(schema ="alumnus", name = "admins")
public class AdminEntity {


    @Id
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "admin_Name")
    private String adminName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "idCard_number")
    private String idCardNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;



}
