package com.surjo.admin.service;

import com.surjo.admin.entity.TxnRequestType;
import com.surjo.admin.model.TransactionRequest;
import com.surjo.admin.model.Type;

import java.util.List;

/**
 * @author sanjoy
 * on 1/3/21
 */

public interface TransactionRequestService {

    TransactionRequest createTransactionRequest(TransactionRequest transactionRequest);

    List<TransactionRequest> getAllTransactionRequest();

    TransactionRequest getTransactionRequestById(Long id);

    void deleteTransactionRequest(Long id);

    List<TransactionRequest> getTransactionRequestByMobile();

    TransactionRequest withdrawTransactionRequest(TransactionRequest transactionRequest);

    TransactionRequest depositTransactionRequest(TransactionRequest transactionRequest);

    List<TransactionRequest> getTransactionRequestTxnRequestType(TxnRequestType txnRequestType);

}
