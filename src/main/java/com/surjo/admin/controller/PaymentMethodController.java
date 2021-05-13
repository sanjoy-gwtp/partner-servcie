package com.surjo.admin.controller;

import com.surjo.admin.model.PaymentMethod;
import com.surjo.admin.model.Type;
import com.surjo.admin.service.PaymentMethodService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/payment-method",produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePaymentMethod(@Valid @RequestBody PaymentMethod paymentMethod) {
        return ResponseEntity.ok(paymentMethodService.createPaymentMethod(paymentMethod));
    }

    @GetMapping
    public ResponseEntity getAllPaymentMethod() {
        List<PaymentMethod> paymentMethods = paymentMethodService.getAllPaymentMethod();
        return ResponseEntity.ok(paymentMethods);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getPaymentMethodById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(paymentMethodService.getPaymentMethodById(id));
    }

    @DeleteMapping
    public ResponseEntity deletePaymentMethod(@RequestParam(name="id",required = true)Long id) {
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.accepted().build();
    }
}
