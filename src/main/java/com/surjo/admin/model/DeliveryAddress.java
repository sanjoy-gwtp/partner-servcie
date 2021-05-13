package com.surjo.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class DeliveryAddress implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String email;
    private String phoneNumber;
}
