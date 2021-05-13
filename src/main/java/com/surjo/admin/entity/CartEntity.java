package com.surjo.admin.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.Generated;
import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customer;
    @ManyToOne
    private ProductEntity product;
    private Long quantity;

}
