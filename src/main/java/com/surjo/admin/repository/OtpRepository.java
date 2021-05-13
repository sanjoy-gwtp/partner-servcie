package com.surjo.admin.repository;

import com.surjo.admin.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<OtpEntity,Long> {
    OtpEntity findOtpEntityByOtp(String otp);
}
