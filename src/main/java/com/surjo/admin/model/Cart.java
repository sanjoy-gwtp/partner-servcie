package com.surjo.admin.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class Cart implements Serializable {
    private Long id;
    private String customer;
    private Product product;
    private Long quantity;
}
