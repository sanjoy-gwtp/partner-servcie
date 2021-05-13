package com.surjo.admin.model;

import com.surjo.admin.entity.NIDType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class Profile implements Serializable {
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
    private byte[] image;
    private String imageType;
    private String imageName;
}
