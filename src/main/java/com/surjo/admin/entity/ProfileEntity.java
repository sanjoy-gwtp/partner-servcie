package com.surjo.admin.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "profile")
@Getter
@Setter
@Accessors(chain = true)
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String fatherName;
    private String motherName;
    private String spouseName;
    private Date dateOfBirth;
    private String bloodGroup;
    private String profession;
    @Enumerated(EnumType.STRING)
    private NIDType nidType;
    private String nid;
    private String dob;
    private String presentAddress;
    private String permanentAddress;
    private String mailingAddress;
    private String nomineeName;
    private String nomineePhoneNumber;
    private String dealerArea;
    private int position;
    @Lob
    private byte[] image;
    private String imageType;
    private String imageName;
}
