package com.lenscart.service;
 
import com.lenscart.dto.FramesDTO;
import com.lenscart.dto.LensesDTO;
import com.lenscart.entity.Admin;
import com.lenscart.exception.InvalidCredentialsException;
import com.lenscart.exception.UserNotFoundException;
import com.lenscart.feign.*;
import com.lenscart.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
 
import java.lang.reflect.Field;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class AdminServiceImplTest {
 
    @Mock private FramesFeignClient framesFeignClient;
    @Mock private LensesFeignClient lensesFeignClient;
    @Mock private GlassFeignClient glassFeignClient;
    @Mock private SunGlassFeignClient sunGlassFeignClient;
    @Mock private AdminRepository adminRepository;
    @Mock private ProductServiceClient productServiceClient;
    @Mock private OrderServiceClient orderServiceClient;
    @Mock private PasswordEncoder passwordEncoder;
 
    private AdminServiceImpl adminService;
 
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
 
        adminService = new AdminServiceImpl(
                adminRepository,
                productServiceClient,
                orderServiceClient,
                passwordEncoder
        );
 
        // Inject @Autowired fields using reflection
        injectField(adminService, "framesFeignClient", framesFeignClient);
        injectField(adminService, "lensesFeignClient", lensesFeignClient);
        injectField(adminService, "glassFeignClient", glassFeignClient);
        injectField(adminService, "sunglassFeignClient", sunGlassFeignClient);
    }
 
    private void injectField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
 
    @Test
    void testAddFrame() {
        FramesDTO frame = new FramesDTO();
        when(framesFeignClient.addFrame(frame)).thenReturn(frame);
 
        FramesDTO result = adminService.addFrame(frame);
 
        assertEquals(frame, result);
        verify(framesFeignClient).addFrame(frame);
    }
 
    @Test
    void testUpdateLens() {
        LensesDTO lens = new LensesDTO("L001", "Crizal", "lens.jpg", "Round", "Clear", 1500.0, 5, "Crizal Clear Lens");
        when(lensesFeignClient.updateLens("L001", lens)).thenReturn(lens);
 
        LensesDTO result = adminService.updateLens("L001", lens);
 
        assertEquals(lens, result);
        verify(lensesFeignClient).updateLens("L001", lens);
    }
 
    @Test
    void testLoginAdmin_Success() {
        Admin admin = new Admin();
        admin.setEmail("admin@example.com");
        admin.setPassword("encodedPass");
 
        when(adminRepository.findByEmail("admin@example.com")).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches("rawPass", "encodedPass")).thenReturn(true);
 
        boolean result = adminService.loginAdmin("admin@example.com", "rawPass");
 
        assertTrue(result);
    }
 
    @Test
    void testLoginAdmin_UserNotFound() {
        when(adminRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());
 
        assertThrows(UserNotFoundException.class, () ->
                adminService.loginAdmin("notfound@example.com", "pass"));
    }
}
 