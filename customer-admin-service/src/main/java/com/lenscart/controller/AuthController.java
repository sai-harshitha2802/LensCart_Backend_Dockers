package com.lenscart.controller;

import com.lenscart.dto.AuthResponse;
import com.lenscart.dto.CustomerDTO;
import com.lenscart.dto.LoginRequest;
import com.lenscart.entity.Admin;
import com.lenscart.entity.Customer;
import com.lenscart.entity.Role;
import com.lenscart.repository.AdminRepository;
import com.lenscart.repository.CustomerRepository;
import com.lenscart.security.JwtUtil;
import com.lenscart.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                          AdminRepository adminRepository, CustomerRepository customerRepository,
                          PasswordEncoder passwordEncoder,CustomerService customerService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerService=customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    	Customer customer = customerService.getCustomerById(id);
    	return ResponseEntity.ok(customer);
    }

 // Register admin
    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin( @Valid @RequestBody Admin admin) {
        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
        	return ResponseEntity.status(409).body("Email already exists");
        }

     // Encrypt password before saving
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(Role.ADMIN);
        adminRepository.save(admin);

        return ResponseEntity.ok("Admin registered successfully!");
    }

    // Register customer
    @PostMapping("/register/customer")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        if (customerRepository.findByEmail(customerDTO.getEmail()).isPresent()) {
        	return ResponseEntity.status(409).body("Email already exists");
        }

     // Convert DTO to Entity and encrypt password
        Customer customer = new Customer(
            customerDTO.getName(),
            customerDTO.getEmail(),
            passwordEncoder.encode(customerDTO.getPassword()), // Encrypt password
            customerDTO.getPhoneNumber(),
            Role.CUSTOMER
        );

        customerRepository.save(customer);

        return ResponseEntity.ok("Customer registered successfully!");
    }


 // Login with JWT token generation
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        // ✅ **Find user role**
        Optional<Customer> customerOpt = customerRepository.findByEmail(loginRequest.getEmail());
        Optional<Admin> adminOpt = adminRepository.findByEmail(loginRequest.getEmail());

        String role = customerOpt.map(customer -> customer.getRole().name())
                .orElse(adminOpt.map(admin -> admin.getRole().name()).orElse("UNKNOWN"));

        // ✅ **Generate JWT token with role**
        String token = jwtUtil.generateToken(authentication.getName(), role);

        Long customerId = null;
        if (customerOpt.isPresent()) {
            customerId = customerOpt.get().getId(); // 👈 Get customerId if role is CUSTOMER
        }

        return ResponseEntity.ok(new AuthResponse(token, "Login successful!", role, customerId));
        
       // return ResponseEntity.ok(new AuthResponse(token, "Login successful!", role));
    }
}
