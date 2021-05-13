package com.surjo.admin.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "delivery_address")
@Getter
@Setter
@Accessors(chain = true)
public class DeliveryAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
