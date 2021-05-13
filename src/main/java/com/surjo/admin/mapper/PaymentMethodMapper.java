package com.surjo.admin.mapper;

import com.surjo.admin.entity.PaymentMethodEntity;
import com.surjo.admin.model.PaymentMethod;
import com.surjo.admin.repository.PaymentMethodRepository;
import com.surjo.asset.common.ResultMapper;
import org.springframework.stereotype.Component;

/**
 * @author sanjoy
 * on 1/3/21
 */

@Component
public class PaymentMethodMapper {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodMapper(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public ResultMapper<PaymentMethodEntity, PaymentMethod> entityToDomainMapper() {
        return entity -> new PaymentMethod()
                .setId(entity.getId())
                .setName(entity.getName());
    }

    public ResultMapper<PaymentMethod, PaymentMethodEntity> domainToEntityMapper() {
        return domain ->
                paymentMethodRepository.findById(domain.getId()).orElse(new PaymentMethodEntity())
                        .setId(domain.getId())
                        .setName(domain.getName());
    }

}
