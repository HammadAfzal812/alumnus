package edu.imdadia.alumnus.entity;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(schema ="alumnus", name = "admin")
public class AdminEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DataManagement_SEQ_GEN")
    @SequenceGenerator(name = "DataManagement_SEQ_GEN", sequenceName = "DataManagement_ID_SEQ")
    @Id
    @Column(name = "admin_id")
    private Integer adminId;
    @Column(name ="district")
    private String district;
    @Column(name = "admin_Name")
    private String adminName;
    @Column(name ="user_Name")
    private String userName;

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
