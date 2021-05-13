package com.surjo.admin.repository;

import com.surjo.admin.entity.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sanjoy
 * on 1/3/21
 */

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity,Long> {

}
