package com.example.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.PaymentService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepo;
    private final PaymentService paymentService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        String status = paymentService.processPayment(order.getTotalPrice());
        order.setStatus(status);
        return orderRepo.save(order);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderRepo.findAll();
    }
}
