package com.surjo.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.surjo.admin.entity.OrderEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(value = { "order" })
public class OrderItem implements Serializable {
    private Long id;
    private Long productId;
    private Long quantity;
    private BigDecimal price;
    private String productName;
    private Order order;
}
