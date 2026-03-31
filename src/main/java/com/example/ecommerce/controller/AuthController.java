
package com.example.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

 private final UserRepository userRepo;
 private final JwtUtil jwtUtil;
 private final PasswordEncoder encoder;

 @PostMapping("/register")
 public String register(@RequestBody User user){
  user.setPassword(encoder.encode(user.getPassword()));
  userRepo.save(user);
  return "Registered";
 }

 @PostMapping("/login")
 public String login(@RequestBody User user){
  User db = userRepo.findByUsername(user.getUsername());
  if(db!=null && encoder.matches(user.getPassword(), db.getPassword())){
   return jwtUtil.generateToken(user.getUsername());
  }
  throw new RuntimeException("Invalid");
 }
}
