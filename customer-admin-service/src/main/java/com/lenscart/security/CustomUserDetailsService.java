package com.lenscart.security;

import com.lenscart.entity.Admin;
import com.lenscart.entity.Customer;
import com.lenscart.repository.AdminRepository;
import com.lenscart.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;

    public CustomUserDetailsService(AdminRepository adminRepository, CustomerRepository customerRepository) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin != null) {
            return new CustomUserDetails(admin.getEmail(), admin.getPassword(), "ROLE_ADMIN");
        }

        Customer customer = customerRepository.findByEmail(email).orElse(null);
        if (customer != null) {
            return new CustomUserDetails(customer.getEmail(), customer.getPassword(), "ROLE_CUSTOMER");
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
