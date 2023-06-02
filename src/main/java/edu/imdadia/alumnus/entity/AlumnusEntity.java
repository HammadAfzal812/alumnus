package edu.imdadia.alumnus.entity;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(schema ="alumnus", name = "alumnus")
public class AlumnusEntity {

    @Id
    @Column(name = "alumnus_id")
    private Integer alumnusId;

    @Column(name = "alumnus_Name")
    private String alumnusName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "idCard_number")
    private String idCardNumber;

    @Column(name = "permanent_Address")
    private String permanentAddress;

    @Column(name = "district")
    private String district;

    @Column(name = "graduation_year")
    private String graduationYear;

    @Column(name = "Type")
    private String type;

}
