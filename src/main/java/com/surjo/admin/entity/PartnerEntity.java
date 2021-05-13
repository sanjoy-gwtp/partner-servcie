package com.surjo.admin.entity;


import com.surjo.asset.repository.schema.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="partner")
public class PartnerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long level;
    private Long parent;
    private Long position;
    private Long levelPosition;
    private String customerName;
    private String mobileNumber;
    private String referenceNumber;
    private String email;
    private String dealerArea;
    private String dealerCode;
    private BigDecimal accountBalance;
    private BigDecimal purchaseBalance;
    private BigDecimal leanBalance;
    @Enumerated(EnumType.STRING)
    private PartnerStatus partnerStatus;
}
