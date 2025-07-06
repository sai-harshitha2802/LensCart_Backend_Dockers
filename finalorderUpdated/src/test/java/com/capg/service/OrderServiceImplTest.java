//package com.capg.service;
// 
//import com.capg.dto.CartDTO;
//import com.capg.dto.OrderDTO;
//import com.capg.dto.OrderItemDTO;
//import com.capg.dto.PaymentResponse;
//import com.capg.entity.Order;
//import com.capg.entity.OrderItem;
//import com.capg.entity.OrderStatus;
//import com.capg.entity.PaymentStatus;
//import com.capg.feign.CartFeignClient;
//import com.capg.repository.OrderItemRepository;
//import com.capg.repository.OrderRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
// 
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
// 
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
// 
// 
//public class OrderServiceImplTest {
// 
//    @Mock
//    private OrderRepository orderRepository;
// 
//    @Mock
//    private OrderItemRepository orderItemRepository;
// 
//    @Mock
//    private CartFeignClient cartClient;
// 
//    @InjectMocks
//    private OrderServiceImpl orderService;
// 
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
// 
//   
//        @Test
//        public void testDeletePendingOrder_Failed() {
//            Long orderId = 1L;
//            Order order = new Order();
//            order.setOrderId(orderId);
//            order.setPaymentStatus(PaymentStatus.COMPLETED);
//            order.setOrderStatus(OrderStatus.DELIVERED);
// 
//            when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
// 
//            boolean isDeleted = orderService.deletePendingOrder(orderId);
// 
//            assertFalse(isDeleted);
//            verify(orderRepository, times(1)).findById(orderId);
//            verify(orderRepository, never()).deleteById(orderId);
//        } 
//
// 
// 
//    @Test
//    public void testGetCustomerOrders() {
//        Long customerId = 1L;
//        Order order = new Order();
//        order.setOrderId(1L);
//        order.setCustomerId(customerId);
//        order.setAddress("123 Street");
//        order.setOrderDateTime(LocalDateTime.now());
//        order.setTotalAmount(200.0);
//        order.setPaymentStatus(PaymentStatus.PENDING);
//        order.setOrderStatus(OrderStatus.PROCESSING);
// 
//        when(orderRepository.findByCustomerIdOrderByOrderDateTimeDesc(customerId)).thenReturn(Arrays.asList(order));
//        when(orderItemRepository.findByOrderId(order.getOrderId())).thenReturn(Arrays.asList(new OrderItem()));
// 
//        List<OrderDTO> orders = orderService.getCustomerOrders(customerId);
// 
//        assertNotNull(orders);
//        assertEquals(1, orders.size());
//        assertEquals(customerId, orders.get(0).getCustomerId());
// 
//        verify(orderRepository, times(1)).findByCustomerIdOrderByOrderDateTimeDesc(customerId);
//        verify(orderItemRepository, times(1)).findByOrderId(order.getOrderId());
//    }
// 
//    @Test
//    public void testGetAllOrders() {
//        Order order = new Order();
//        order.setOrderId(1L);
//        order.setCustomerId(1L);
//        order.setAddress("123 Street");
//        order.setOrderDateTime(LocalDateTime.now());
//        order.setTotalAmount(200.0);
//        order.setPaymentStatus(PaymentStatus.PENDING);
//        order.setOrderStatus(OrderStatus.PROCESSING);
// 
//        when(orderRepository.findAllByOrderByOrderDateTimeDesc()).thenReturn(Arrays.asList(order));
//        when(orderItemRepository.findByOrderId(order.getOrderId())).thenReturn(Arrays.asList(new OrderItem()));
// 
//        List<OrderDTO> orders = orderService.getAllOrders();
// 
//        assertNotNull(orders);
//        assertEquals(1, orders.size());
//        assertEquals(1L, orders.get(0).getCustomerId());
// 
//        verify(orderRepository, times(1)).findAllByOrderByOrderDateTimeDesc();
//        verify(orderItemRepository, times(1)).findByOrderId(order.getOrderId());
//    }
// 
//    @Test
//    public void testMakePayment() {
//        Long orderId = 1L;
//        double paymentAmount = 200.0;
//        Order order = new Order();
//        order.setOrderId(orderId);
//        order.setCustomerId(1L);
//        order.setTotalAmount(paymentAmount);
//        order.setPaymentStatus(PaymentStatus.PENDING);
// 
//        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
// 
//        PaymentResponse paymentResponse = orderService.makePayment(orderId, paymentAmount);
// 
//        assertNotNull(paymentResponse);
//        assertEquals("Completed", paymentResponse.getPaymentStatus());
//        assertEquals("Payment successful.", paymentResponse.getMessage());
// 
//        verify(orderRepository, times(1)).findById(orderId);
//        verify(orderRepository, times(1)).save(order);
//    }
// 
//    @Test
//    public void testGetPaidOrdersForAdmin() {
//        Order order = new Order();
//        order.setOrderId(1L);
//        order.setCustomerId(1L);
//        order.setAddress("123 Street");
//        order.setOrderDateTime(LocalDateTime.now());
//        order.setTotalAmount(200.0);
//        order.setPaymentStatus(PaymentStatus.COMPLETED);
//        order.setOrderStatus(OrderStatus.PROCESSING);
// 
//        when(orderRepository.findByPaymentStatusAndOrderStatusNotIn(PaymentStatus.COMPLETED, Arrays.asList(OrderStatus.DELIVERED, OrderStatus.SHIPPED)))
//                .thenReturn(Arrays.asList(order));
//        when(orderItemRepository.findByOrderId(order.getOrderId())).thenReturn(Arrays.asList(new OrderItem()));
// 
//        List<OrderDTO> orders = orderService.getPaidOrdersForAdmin();
// 
//        assertNotNull(orders);
//        assertEquals(1, orders.size());
//        assertEquals(1L, orders.get(0).getCustomerId());
// 
//        verify(orderRepository, times(1)).findByPaymentStatusAndOrderStatusNotIn(PaymentStatus.COMPLETED, Arrays.asList(OrderStatus.DELIVERED, OrderStatus.SHIPPED));
//        verify(orderItemRepository, times(1)).findByOrderId(order.getOrderId());
//    }
//}