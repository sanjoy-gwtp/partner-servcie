package com.surjo.admin.mapper;

import com.surjo.admin.entity.PartnerEntity;
import com.surjo.admin.model.Partner;
import com.surjo.admin.repository.PartnerRepository;
import com.surjo.asset.common.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapper {

    private final PartnerRepository partnerRepository;

    public PartnerMapper(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public ResultMapper<PartnerEntity, Partner> entityToDomainMapper() {
        return entity -> new Partner()
                .setId(entity.getId())
                .setLevel(entity.getLevel())
                .setCustomerName(entity.getCustomerName())
                .setMobileNumber(entity.getMobileNumber())
                .setReferenceNumber(entity.getReferenceNumber())
                .setEmail(entity.getEmail())
                .setDealerArea(entity.getDealerArea())
                .setDealerCode(entity.getDealerCode())
                .setParent(entity.getParent());
    }

    public ResultMapper<Partner, PartnerEntity> domainToEntityMapper() {
        return domain ->
                partnerRepository.findById(domain.getId()).orElse(new PartnerEntity())
                        .setId(domain.getId())
                        .setLevel(domain.getLevel())
                        .setCustomerName(domain.getCustomerName())
                        .setMobileNumber(domain.getMobileNumber())
                        .setReferenceNumber(domain.getReferenceNumber())
                        .setEmail(domain.getEmail())
                        .setDealerArea(domain.getDealerArea())
                        .setDealerCode(domain.getDealerCode())
                        .setParent(domain.getParent());
    }
}
