package com.surjo.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.surjo.admin.entity.PartnerEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(value = { "partnerEntityList" })
public class Target {
    private int level;
    private Long target;
    private Long achievement;
    private float achievementInPercentage;
    private List<PartnerEntity> partnerEntityList;
}
