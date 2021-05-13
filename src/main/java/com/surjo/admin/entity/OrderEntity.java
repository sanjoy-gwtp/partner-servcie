package com.surjo.admin.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="partner_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customer;
    private BigDecimal salesPrice;
    private String paymentReference;
    private String paymentChanel;
    @Enumerated(EnumType.STRING)
    private TransactionStatus txnStatus;
    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> items;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Date placeDate;
    private Date approvedDate;
    private Date rejectDate;
    private Date deliveryDate;
    @OneToOne
    private DeliveryAddressEntity deliveryAddress;
}
