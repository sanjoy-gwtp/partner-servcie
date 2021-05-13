package com.surjo.admin.model;

import com.surjo.admin.entity.TxnRequestStatus;
import com.surjo.admin.entity.TxnRequestType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author sanjoy
 * on 1/3/21
 */
@Getter
@Setter
@Accessors(chain = true)
public class TransactionRequest implements Serializable {
    private Long id;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private TxnRequestType txnRequestType;
    private TxnRequestStatus txnRequestStatus;
    private String  partnerNumber;

}
