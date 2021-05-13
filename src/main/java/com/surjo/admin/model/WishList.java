package com.surjo.admin.model;

import com.surjo.admin.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class WishList implements Serializable {
    private Long id;
    private String customer;
    private Product product;
}
