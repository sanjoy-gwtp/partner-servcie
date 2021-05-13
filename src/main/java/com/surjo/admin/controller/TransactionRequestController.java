package com.surjo.admin.controller;

import com.surjo.admin.entity.TxnRequestType;
import com.surjo.admin.model.Cart;
import com.surjo.admin.model.TransactionRequest;
import com.surjo.admin.service.TransactionRequestService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/transaction-request",produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionRequestController {

    private final TransactionRequestService transactionRequestService;

    public TransactionRequestController(TransactionRequestService transactionRequestService) {
        this.transactionRequestService = transactionRequestService;
    }


    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTransactionRequest(@Valid @RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionRequestService.createTransactionRequest(transactionRequest));
    }

    @GetMapping
    public ResponseEntity getAllTransactionRequest() {
        List<TransactionRequest> transactionRequestList = transactionRequestService.getAllTransactionRequest();
        return ResponseEntity.ok(transactionRequestList);
    }

    @GetMapping(path = "/mobile")
    public ResponseEntity getTransactionRequests() {
        List<TransactionRequest> transactionRequestList = transactionRequestService.getTransactionRequestByMobile();
        return ResponseEntity.ok(transactionRequestList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getTransactionRequestById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(transactionRequestService.getTransactionRequestById(id));
    }

    @DeleteMapping
    public ResponseEntity deleteTransactionRequest(@RequestParam(name="id",required = true)Long id) {
        transactionRequestService.deleteTransactionRequest(id);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(path = "/withdraw",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> withdrawTransactionRequest(@Valid @RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionRequestService.withdrawTransactionRequest(transactionRequest));
    }

    @RequestMapping(path = "/deposit",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> depositTransactionRequest(@Valid @RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionRequestService.depositTransactionRequest(transactionRequest));
    }

    @GetMapping(path = "/withdraw/list")
    public ResponseEntity getWithdrawList() {
        return ResponseEntity.ok(transactionRequestService.getTransactionRequestTxnRequestType(TxnRequestType.WITHDRAW));
    }

    @GetMapping(path = "/deposit/list")
    public ResponseEntity getDepositList() {
        return ResponseEntity.ok(transactionRequestService.getTransactionRequestTxnRequestType(TxnRequestType.DEPOSIT));
    }

}
