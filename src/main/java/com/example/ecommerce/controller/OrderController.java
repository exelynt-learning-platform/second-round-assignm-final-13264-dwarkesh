
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

 private final OrderRepository repo;
 private final PaymentService payment;

 @PostMapping
 public Order create(@RequestBody Order order){
  order.setStatus(payment.processPayment(order.getTotalPrice()));
  return repo.save(order);
 }

 @GetMapping
 public List<Order> getAll(){
  return repo.findAll();
 }
}
