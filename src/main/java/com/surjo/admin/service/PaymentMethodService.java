package com.surjo.admin.service;

import com.surjo.admin.model.PaymentMethod;
import com.surjo.admin.model.Picture;

import java.util.List;

public interface PaymentMethodService {

    PaymentMethod createPaymentMethod(PaymentMethod paymentMethod);

    List<PaymentMethod> getAllPaymentMethod();

    PaymentMethod getPaymentMethodById(Long id);

    void deletePaymentMethod(Long id);
}
