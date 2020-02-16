package org.erfan.erfanu25.controller;

import org.erfan.erfanu25.domain.Order;
import org.erfan.erfanu25.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/order")
    public List<Order> customer() {
        return orderService.getOrderList();
    }

    @PostMapping("/order")
    public ResponseEntity<Long> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.saveOrder(order));
    }
}
