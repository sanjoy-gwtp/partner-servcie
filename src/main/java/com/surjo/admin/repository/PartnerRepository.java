package com.surjo.admin.repository;

import com.surjo.admin.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepository extends JpaRepository<PartnerEntity,Long> {

    PartnerEntity findPartnerByMobileNumber(String mobileNumber);

    List<PartnerEntity> findPartnerByReferenceNumber(String referenceNumber);

    List<PartnerEntity> findPartnerByLevel(Long level);
}
