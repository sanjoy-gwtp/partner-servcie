package com.surjo.admin.service.impl;

import com.surjo.admin.entity.PaymentMethodEntity;
import com.surjo.admin.model.PaymentMethod;
import com.surjo.admin.model.Picture;
import com.surjo.admin.repository.PaymentMethodRepository;
import com.surjo.admin.service.PaymentMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final ModelMapper modelMapper;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository, ModelMapper modelMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        PaymentMethodEntity paymentMethodEntity=paymentMethodRepository.save(modelMapper.map(paymentMethod,PaymentMethodEntity.class));
        return modelMapper.map(paymentMethodEntity,PaymentMethod.class);
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethod() {
        return paymentMethodRepository.findAll().stream().map(paymentMethodEntity ->
                modelMapper.map(paymentMethodEntity,PaymentMethod.class)).collect(Collectors.toList());
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long id) {
        return modelMapper.map(paymentMethodRepository.getOne(id),PaymentMethod.class);
    }

    @Override
    public void deletePaymentMethod(Long id) {
        paymentMethodRepository.delete(paymentMethodRepository.getOne(id));
    }
}
