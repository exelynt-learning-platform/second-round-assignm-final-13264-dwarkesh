
package com.example.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

 private final ProductRepository repo;

 @PostMapping
 public Product create(@RequestBody Product p){
  return repo.save(p);
 }

 @GetMapping
 public List<Product> getAll(){
  return repo.findAll();
 }
}
