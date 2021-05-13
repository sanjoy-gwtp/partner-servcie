package com.surjo.admin.service;

import com.surjo.admin.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    List<Order> getAllOrderList();

    Order getOrderById(Long id);

    void deleteOrder(Long id);

    List<Order> getOrderByCustomer(String mobileNumber);
}
