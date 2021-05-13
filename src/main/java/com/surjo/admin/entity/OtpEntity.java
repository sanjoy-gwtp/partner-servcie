package com.surjo.admin.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="otp")
public class OtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String otp;
    private String mobileNumber;
    private String userId;

    public OtpEntity() {
    }

    public OtpEntity(String otp, String mobileNumber, String userId) {
        this.otp = otp;
        this.mobileNumber = mobileNumber;
        this.userId = userId;
    }
}
