package com.surjo.admin.repository;

import com.surjo.admin.entity.TransactionRequestEntity;
import com.surjo.admin.entity.TxnRequestType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sanjoy
 * on 1/3/21
 */

public interface TransactionRequestRepository extends JpaRepository<TransactionRequestEntity,Long> {

    List<TransactionRequestEntity> findTransactionRequestEntitiesByPartnerNumber(String partnerNumber);

    List<TransactionRequestEntity> findTransactionRequestEntitiesByPartnerNumberAndTxnRequestType
            (String partnerNumber, TxnRequestType txnRequestType);
}
