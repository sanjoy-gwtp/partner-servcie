package com.surjo.admin.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long quantity;
    private String productName;
    private BigDecimal price;
    @ManyToOne
    private OrderEntity order;
}
