package com.surjo.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sanjoy on 3/9/19.
 */
@Getter
@Setter
@Accessors(chain = true)
public class Product implements Serializable {
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal salePrice;
    private BigDecimal discount;
    private String shortDetails;
    private String description;
    private Long stock;
    private String state;
    private Boolean newPro;
    private String brand;
    private Boolean sale;
    private List<Picture> pictures;
    private Type type;
    private Category category;
}
