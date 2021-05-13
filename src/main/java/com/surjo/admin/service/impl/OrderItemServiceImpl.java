package com.surjo.admin.service.impl;

import com.surjo.admin.entity.OrderItemEntity;
import com.surjo.admin.model.OrderItem;
import com.surjo.admin.repository.OrderItemRepository;
import com.surjo.admin.repository.OrderRepository;
import com.surjo.admin.service.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public OrderItem createOrderItem(OrderItem orderItem) {
        OrderItemEntity orderItemEntity=orderItemRepository.save(modelMapper.map(orderItem, OrderItemEntity.class));
        orderItemEntity.setOrder( orderRepository.getOne(orderItem.getOrder().getId()));
        return modelMapper.map(orderItemEntity,OrderItem.class);
    }
}
