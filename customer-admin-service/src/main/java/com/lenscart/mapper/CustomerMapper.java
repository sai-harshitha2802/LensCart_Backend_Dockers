package com.lenscart.mapper;

import com.lenscart.dto.CustomerDTO;
import com.lenscart.entity.Customer;
import com.lenscart.entity.Role;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(
                
                customer.getName(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getPhoneNumber()
        );
    }

    public static Customer toEntity(CustomerDTO customerDTO) {
        return new Customer(
                
                customerDTO.getName(),
                customerDTO.getEmail(),
                customerDTO.getPassword(),
                customerDTO.getPhoneNumber(),
                Role.CUSTOMER // Ensure Role is explicitly set
        );
    }
}
