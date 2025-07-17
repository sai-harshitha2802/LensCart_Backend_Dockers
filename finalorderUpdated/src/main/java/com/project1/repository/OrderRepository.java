package com.project1.repository;
 
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.project1.entity.Order;
import com.project1.entity.OrderStatus;
import com.project1.entity.PaymentStatus;
 
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerIdOrderByOrderDateTimeDesc(Long customerId);

    List<Order> findAllByOrderByOrderDateTimeDesc();

// Count orders by payment and order status

 
    

    List<Order> findByPaymentStatusAndOrderStatusNotIn(PaymentStatus completed, List<OrderStatus> list);

    Order findFirstByRazorpayOrderId(String razorpayOrderId);

 
}

 