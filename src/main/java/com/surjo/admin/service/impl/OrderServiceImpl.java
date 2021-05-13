package com.surjo.admin.service.impl;

import com.surjo.admin.entity.DeliveryAddressEntity;
import com.surjo.admin.entity.OrderEntity;
import com.surjo.admin.entity.OrderStatus;
import com.surjo.admin.entity.PartnerEntity;
import com.surjo.admin.model.DeliveryAddress;
import com.surjo.admin.model.Order;
import com.surjo.admin.model.OrderItem;
import com.surjo.admin.repository.DeliveryAddressRepository;
import com.surjo.admin.repository.OrderRepository;
import com.surjo.admin.repository.PartnerRepository;
import com.surjo.admin.service.OrderItemService;
import com.surjo.admin.service.OrderService;
import com.surjo.security.core.CustomSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final ModelMapper modelMapper;
    private final PartnerRepository partnerRepository;
    private final CustomSession session;
    private final DeliveryAddressRepository deliveryAddressRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService, ModelMapper modelMapper, PartnerRepository partnerRepository, CustomSession session, DeliveryAddressRepository deliveryAddressRepository) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.modelMapper = modelMapper;
        this.partnerRepository = partnerRepository;
        this.session = session;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }


    @Override
    public Order createOrder(Order order) {
        List<OrderItem> orderItems=order.getItems();
        OrderEntity orderEntity=modelMapper.map(getOrderDate(order),OrderEntity.class);
        orderEntity.setDeliveryAddress(deliveryAddressRepository.save(modelMapper.map(order.getDeliveryAddress(), DeliveryAddressEntity.class)));
        orderEntity= orderRepository.save(orderEntity);
        PartnerEntity partnerEntity=partnerRepository.findPartnerByMobileNumber(session.getUsername());
        if(order.getOrderStatus().equals(OrderStatus.PENDING)) {
            partnerEntity.setPurchaseBalance(partnerEntity.getPurchaseBalance().subtract(order.getSalesPrice()));
        }
        for(OrderItem orderItem:orderItems) {
            orderItem.setOrder(modelMapper.map(orderEntity,Order.class));
            orderItemService.createOrderItem(orderItem);
        }
        return modelMapper.map(orderEntity,Order.class);
    }
    private Order getOrderDate(Order order){
        switch (order.getOrderStatus()){
            case PENDING:
                order.setPlaceDate(new Date());
                break;
            case CONFIRM:
                order.setApprovedDate(new Date());
                break;
            case REJECTED:
                order.setRejectDate(new Date());
                break;
            case DELIVERED:
                order.setDeliveryDate(new Date());
                break;
        }
        return order;
    }

    @Override
    public List<Order> getAllOrderList() {
        return orderRepository.findAll().stream().map(orderEntity ->
                modelMapper.map(orderEntity,Order.class)).collect(Collectors.toList());
    }

    @Override
    public Order getOrderById(Long id) {
        return modelMapper.map(orderRepository.getOne(id),Order.class);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.delete(orderRepository.getOne(id));
    }

    @Override
    public List<Order> getOrderByCustomer(String mobileNumber) {
        return orderRepository.findOrderEntityByCustomer(mobileNumber).stream().map(orderEntity ->
                modelMapper.map(orderEntity,Order.class)).collect(Collectors.toList());
    }
}
