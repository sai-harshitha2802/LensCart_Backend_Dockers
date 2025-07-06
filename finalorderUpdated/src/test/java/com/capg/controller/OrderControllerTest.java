package com.capg.controller;
 
import com.capg.dto.OrderDTO;
import com.capg.dto.OrderStatusUpdateRequest;
import com.capg.dto.PaymentRequest;
import com.capg.dto.PaymentResponse;
import com.capg.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import java.util.Arrays;
import java.util.List;
 
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
public class OrderControllerTest {
 
    private MockMvc mockMvc;
 
    @Mock
    private OrderService orderService;
 
    @InjectMocks
    private OrderController orderController;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }
 
    
    @Test
    public void testGetCustomerOrders() throws Exception {
        List<OrderDTO> orders = Arrays.asList(new OrderDTO(), new OrderDTO());
        when(orderService.getCustomerOrders(anyLong())).thenReturn(orders);
 
        mockMvc.perform(get("/order/customer")
                .param("customerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(orders.size()));
 
        verify(orderService, times(1)).getCustomerOrders(1L);
    }
 
    @Test
    public void testGetAllOrders() throws Exception {
        List<OrderDTO> orders = Arrays.asList(new OrderDTO(), new OrderDTO());
        when(orderService.getAllOrders()).thenReturn(orders);
 
        mockMvc.perform(get("/order/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(orders.size()));
 
        verify(orderService, times(1)).getAllOrders();
    }
 
    
    @Test
    public void testGetPaidOrders() throws Exception {
        List<OrderDTO> orders = Arrays.asList(new OrderDTO(), new OrderDTO());
        when(orderService.getPaidOrdersForAdmin()).thenReturn(orders);
 
        mockMvc.perform(get("/order/paid-orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(orders.size()));
 
        verify(orderService, times(1)).getPaidOrdersForAdmin();
    }
 
    @Test
    public void testUpdateOrderStatusBulk() throws Exception {
        OrderStatusUpdateRequest request = new OrderStatusUpdateRequest();
        request.setOrderIds(Arrays.asList(1L, 2L));
        request.setStatus("SHIPPED");
 
        mockMvc.perform(put("/order/update-order-status")
                .contentType("application/json")
                .content("{\"orderIds\": [1, 2], \"status\": \"SHIPPED\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order statuses updated successfully."));
 
        verify(orderService, times(1)).updateOrderStatusBulk(Arrays.asList(1L, 2L), "SHIPPED");
    }
 
    @Test
    public void testDeletePendingOrder() throws Exception {
        when(orderService.deletePendingOrder(anyLong())).thenReturn(true);
 
        mockMvc.perform(delete("/order/delete/{orderId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Order successfully deleted."));
 
        verify(orderService, times(1)).deletePendingOrder(1L);
    }
 
   
}