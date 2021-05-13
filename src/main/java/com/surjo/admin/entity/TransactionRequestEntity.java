package com.surjo.admin.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transaction_request")
@Getter
@Setter
@Accessors(chain = true)
public class TransactionRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethodEntity paymentMethod;

    @Enumerated(EnumType.STRING)
    private TxnRequestType txnRequestType;

    @Enumerated(EnumType.STRING)
    private TxnRequestStatus txnRequestStatus;

    private String  partnerNumber;


}
