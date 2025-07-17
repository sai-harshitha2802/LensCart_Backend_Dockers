//package com.capg.service;
//
//import java.util.List;
//
//import com.capg.dto.OrderDTO;
//import com.capg.entity.Order;
//
//public interface OrderService {
//	public List<Order> placeOrder(Long customerId);;
//	public List<Order> getOrdersByCustomer(Long customerId);
//	public Order getOrderById(Long orderId);
//	public Order updateOrderStatus(Long orderId, String status);
//
//}


package com.project1.service;
 
import java.util.List;

import com.project1.dto.OrderDTO;
import com.project1.dto.PaymentResponse;
import com.project1.dto.RazorpayVerificationRequest;
 
public interface OrderService {
 
    // Customer places an order using their email and address

    OrderDTO placeOrder(Long customerId, String address);
 
    // Get all orders of a customer, sorted by latest order date

    List<OrderDTO> getCustomerOrders(Long customerId);
 
    // Get all orders for Admin (sorted by latest order date)

    List<OrderDTO> getAllOrders();
 
    // Make payment for an order

    PaymentResponse makePayment(Long orderId, double paymentAmount);
    
    public String verifyPayment(RazorpayVerificationRequest request);
 
    // Get all paid orders for Admin

    List<OrderDTO> getPaidOrdersForAdmin();
 
    // Update order status in bulk (Admin can update multiple orders)

    void updateOrderStatusBulk(List<Long> orderIds, String status);
 
    // Delete a pending order (if not paid yet)

    boolean deletePendingOrder(Long orderId);

 
	OrderDTO getOrderById(Long orderId);

	void cancelOrder(Long orderId);

}

 