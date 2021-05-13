package com.surjo.admin.controller;

import com.surjo.admin.model.Cart;
import com.surjo.admin.model.Order;
import com.surjo.admin.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/order",produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOrder(@Valid @RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping
    public ResponseEntity getAllOrder() {
        List<Order> orderList = orderService.getAllOrderList();
        return ResponseEntity.ok(orderList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping(path = "/list/{mobileNumber}")
    public ResponseEntity getOrderByCustomer(@PathVariable("mobileNumber") String mobileNumber) {
        return ResponseEntity.ok(orderService.getOrderByCustomer(mobileNumber));
    }

    @DeleteMapping
    public ResponseEntity deleteOrder(@RequestParam(name="id",required = true)Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.accepted().build();
    }
}
