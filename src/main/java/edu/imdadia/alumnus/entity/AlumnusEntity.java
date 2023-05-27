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

    @Column(name = "permanentAddress")
    private String address;

    @Column(name = "district")
    private String district;

    @Column(name = "complete")
    private String complete;

    @Column(name = "Type")
    private String Type;

}
