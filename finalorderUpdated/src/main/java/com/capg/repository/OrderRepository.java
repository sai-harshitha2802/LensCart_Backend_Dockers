package com.capg.repository;
 
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.capg.entity.Order;

import com.capg.entity.OrderStatus;

import com.capg.entity.PaymentStatus;
 
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerIdOrderByOrderDateTimeDesc(Long customerId);

    List<Order> findAllByOrderByOrderDateTimeDesc();

// Count orders by payment and order status

 
    

    List<Order> findByPaymentStatusAndOrderStatusNotIn(PaymentStatus completed, List<OrderStatus> list);

    Order findFirstByRazorpayOrderId(String razorpayOrderId);

 
}

 