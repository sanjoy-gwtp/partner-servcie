package com.surjo.admin.mapper;

import com.surjo.admin.entity.PictureEntity;
import com.surjo.admin.entity.TransactionRequestEntity;
import com.surjo.admin.model.TransactionRequest;
import com.surjo.admin.repository.PaymentMethodRepository;
import com.surjo.admin.repository.TransactionRequestRepository;
import com.surjo.asset.common.ResultMapper;
import org.springframework.stereotype.Component;

/**
 * @author sanjoy
 * on 1/3/21
 */
@Component
public class TransactionRequestMapper {

    private final TransactionRequestRepository transactionRequestRepository;
    private final PaymentMethodMapper paymentMethodMapper;
    private final PaymentMethodRepository paymentMethodRepository;

    public TransactionRequestMapper(TransactionRequestRepository transactionRequestRepository, PaymentMethodMapper paymentMethodMapper, PaymentMethodRepository paymentMethodRepository) {
        this.transactionRequestRepository = transactionRequestRepository;
        this.paymentMethodMapper = paymentMethodMapper;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public ResultMapper<TransactionRequestEntity, TransactionRequest> entityToDomainMapper() {
        return entity -> new TransactionRequest()
                .setId(entity.getId())
                .setAmount(entity.getAmount())
                .setFromAccount(entity.getFromAccount())
                .setToAccount(entity.getToAccount())
                .setPartnerNumber(entity.getPartnerNumber())
                .setTxnRequestStatus(entity.getTxnRequestStatus())
                .setTxnRequestType(entity.getTxnRequestType())
                .setPaymentMethod(paymentMethodMapper.entityToDomainMapper().map(entity.getPaymentMethod()));
    }

    public ResultMapper<TransactionRequest, TransactionRequestEntity> domainToEntityMapper() {
        return domain -> {
            TransactionRequestEntity entity = new TransactionRequestEntity();
            if (domain.getId() != null) {
                entity = transactionRequestRepository.getOne(domain.getId());
            }
            entity.setId(domain.getId())
                    .setAmount(domain.getAmount())
                    .setFromAccount(domain.getFromAccount())
                    .setToAccount(domain.getToAccount())
                    .setPartnerNumber(domain.getPartnerNumber())
                    .setTxnRequestStatus(domain.getTxnRequestStatus())
                    .setTxnRequestType(domain.getTxnRequestType())
                    .setPaymentMethod(paymentMethodRepository.getOne(domain.getPaymentMethod().getId()));
            return entity;
        };
    }
}
