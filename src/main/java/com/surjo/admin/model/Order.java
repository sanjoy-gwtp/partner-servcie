package com.surjo.admin.model;

import com.surjo.admin.entity.DeliveryAddressEntity;
import com.surjo.admin.entity.OrderStatus;
import com.surjo.admin.entity.TransactionStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class Order implements Serializable {
    private Long id;
    private String customer;
    private BigDecimal salesPrice;
    private String paymentReference;
    private String paymentChanel;
    private TransactionStatus txnStatus;
    private List<OrderItem> items;
    private OrderStatus orderStatus;
    private Date placeDate;
    private Date approvedDate;
    private Date rejectDate;
    private Date deliveryDate;
    private DeliveryAddress deliveryAddress;
}
