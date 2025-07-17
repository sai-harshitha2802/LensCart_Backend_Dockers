package com.project1.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.project1.entity.OrderItem;
 
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findByOrderId(Long orderId);
}