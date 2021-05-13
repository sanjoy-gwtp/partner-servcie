package com.surjo.admin.service.impl;

import com.surjo.admin.entity.*;
import com.surjo.admin.exception.InsufficientFundException;
import com.surjo.admin.mapper.TransactionRequestMapper;
import com.surjo.admin.model.TransactionRequest;
import com.surjo.admin.repository.PartnerRepository;
import com.surjo.admin.repository.TransactionRequestRepository;
import com.surjo.admin.service.TransactionRequestService;
import com.surjo.security.core.CustomSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sanjoy
 * on 1/3/21
 */
@Service
public class TransactionRequestServiceImpl implements TransactionRequestService {

    private final TransactionRequestRepository transactionRequestRepository;
    private final PartnerRepository partnerRepository;
    private final TransactionRequestMapper transactionRequestMapper;
    private final CustomSession customSession;

    public TransactionRequestServiceImpl(TransactionRequestRepository transactionRequestRepository, PartnerRepository partnerRepository, TransactionRequestMapper transactionRequestMapper, CustomSession customSession) {
        this.transactionRequestRepository = transactionRequestRepository;
        this.partnerRepository = partnerRepository;
        this.transactionRequestMapper = transactionRequestMapper;
        this.customSession = customSession;
    }

    @Transactional
    public TransactionRequest createTransactionRequest(TransactionRequest transactionRequest) {
        TransactionRequestEntity transactionRequestEntity=transactionRequestRepository.getOne(transactionRequest.getId());
        if(transactionRequestEntity!=null){
            if(transactionRequestEntity.getTxnRequestStatus().equals(TxnRequestStatus.PENDING)){
                if(transactionRequest.getTxnRequestStatus().equals(TxnRequestStatus.CONFIRM)){
                  PartnerEntity partnerEntity=  partnerRepository.findPartnerByMobileNumber(transactionRequest.getPartnerNumber());
                  if(transactionRequest.getTxnRequestType().equals(TxnRequestType.DEPOSIT)) {
                      if(partnerEntity.getPurchaseBalance().compareTo(BigDecimal.ZERO)==0){
                          if(partnerEntity.getPartnerStatus().equals(PartnerStatus.INACTIVE)){
                              partnerEntity.setPartnerStatus(PartnerStatus.ACTIVE);
                              partnerCommission(partnerEntity);
                          }
                      }
                      partnerEntity.setPurchaseBalance(partnerEntity.getPurchaseBalance().add(transactionRequest.getAmount()));
                  }else {
                      if(partnerEntity.getAccountBalance().compareTo(transactionRequest.getAmount())>=0) {
                          partnerEntity.setAccountBalance(partnerEntity.getAccountBalance().subtract(transactionRequest.getAmount()));
                        }else {
                          throw new InsufficientFundException();
                        }
                      }
                }
            }
        }
        return transactionRequestMapper.entityToDomainMapper().map(
                transactionRequestRepository.save(
                        transactionRequestMapper.domainToEntityMapper().map(transactionRequest)));
    }

    private void partnerCommission(PartnerEntity partnerEntity) {
        if(partnerEntity.getLevelPosition()%4==0){
            PartnerEntity parent=partnerRepository.findPartnerByMobileNumber(partnerEntity.getReferenceNumber());
            int level=partnerEntity.getLevel().intValue();
            int count=0;
            while (level>0){
                if(count!=0) {
                    parent = partnerRepository.findPartnerByMobileNumber(parent.getReferenceNumber());
                }
                level=level-1;
                count=count+1;
                if(count==1){
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(10)));
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(10)));
                    if(partnerEntity.getLevelPosition().intValue()!=Math.pow(4,partnerEntity.getLevel())){
                        break;
                    }
                }else if(count==2){
                    double amount=3*Math.pow(4,partnerEntity.getLevel());
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(amount/2)));
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(amount/2)));
                }else if(count==3){
                    double amount=2*Math.pow(4,partnerEntity.getLevel());
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(amount/2)));
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(amount/2)));
                }else {
                    double amount=1*Math.pow(4,partnerEntity.getLevel());
                    parent.setAccountBalance(parent.getAccountBalance().add(BigDecimal.valueOf(amount/2)));
                    parent.setPurchaseBalance(parent.getPurchaseBalance().add(BigDecimal.valueOf(amount/2)));
                }
            }
        }
    }

    @Override
    public List<TransactionRequest> getAllTransactionRequest() {
        return transactionRequestRepository.findAll()
                .stream().map(transactionRequestEntity ->
                        transactionRequestMapper.entityToDomainMapper()
                                .map(transactionRequestEntity)).collect(Collectors.toList());
    }

    @Override
    public TransactionRequest getTransactionRequestById(Long id) {
        return transactionRequestMapper.entityToDomainMapper().map(transactionRequestRepository.getOne(id));
    }

    @Override
    public void deleteTransactionRequest(Long id) {
        transactionRequestRepository.delete(transactionRequestRepository.getOne(id));
    }

    @Override
    public List<TransactionRequest> getTransactionRequestByMobile() {
        String mobileNumber=customSession.getUsername();
        return transactionRequestRepository.findTransactionRequestEntitiesByPartnerNumber(mobileNumber)
                .stream().map(transactionRequestEntity ->
                        transactionRequestMapper.entityToDomainMapper()
                                .map(transactionRequestEntity)).collect(Collectors.toList());
    }

    @Override
    public TransactionRequest withdrawTransactionRequest(TransactionRequest transactionRequest) {
        String partnerNumber=customSession.getUsername();
        PartnerEntity partnerEntity=partnerRepository.findPartnerByMobileNumber(partnerNumber);
        TransactionRequestEntity transactionRequestEntity=transactionRequestMapper.domainToEntityMapper().map(transactionRequest);
        if(partnerEntity.getAccountBalance().compareTo(transactionRequest.getAmount())>=0){
            transactionRequestEntity.setTxnRequestType(TxnRequestType.WITHDRAW);
            transactionRequestEntity.setPartnerNumber(partnerNumber);
            transactionRequestEntity.setTxnRequestStatus(TxnRequestStatus.PENDING);
            transactionRequestRepository.save(transactionRequestEntity);
        }else {
            throw new InsufficientFundException();
        }
        return transactionRequestMapper.entityToDomainMapper().map(transactionRequestEntity);
    }

    @Override
    public TransactionRequest depositTransactionRequest(TransactionRequest transactionRequest) {
        String partnerNumber=customSession.getUsername();
        TransactionRequestEntity transactionRequestEntity=transactionRequestMapper.domainToEntityMapper().map(transactionRequest);
        transactionRequestEntity.setTxnRequestType(TxnRequestType.DEPOSIT);
        transactionRequestEntity.setPartnerNumber(partnerNumber);
        transactionRequestEntity.setTxnRequestStatus(TxnRequestStatus.PENDING);
        transactionRequestRepository.save(transactionRequestEntity);
        return transactionRequestMapper.entityToDomainMapper().map(transactionRequestEntity);
    }

    @Override
    public List<TransactionRequest> getTransactionRequestTxnRequestType(TxnRequestType txnRequestType) {
        return transactionRequestRepository.
                findTransactionRequestEntitiesByPartnerNumberAndTxnRequestType
                        (customSession.getUsername(),txnRequestType).stream().map(transactionRequestEntity ->
                transactionRequestMapper.entityToDomainMapper().map(transactionRequestEntity)
                ).collect(Collectors.toList());
    }
}
