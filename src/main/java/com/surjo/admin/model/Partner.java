package com.surjo.admin.model;

import com.surjo.admin.entity.PartnerEntity;
import com.surjo.admin.entity.PartnerStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class Partner implements Serializable {
    private Long id;
    private Long level;
    private Long position;
    private Long levelPosition;
    private Long parent;
    private String customerName;
    private String password;
    private String mobileNumber;
    private String referenceNumber;
    private String email;
    private String dealerArea;
    private String dealerCode;
    private BigDecimal accountBalance;
    private BigDecimal purchaseBalance;
    private BigDecimal leanBalance;
    private PartnerStatus partnerStatus;
}
