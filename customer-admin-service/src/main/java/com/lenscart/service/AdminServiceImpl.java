package com.lenscart.service;

import com.lenscart.dto.FramesDTO;
import com.lenscart.dto.GlassDTO;
import com.lenscart.dto.LensesDTO;
import com.lenscart.dto.ProductDTO;
import com.lenscart.dto.SunGlassDTO;
import com.lenscart.entity.Admin;
import com.lenscart.exception.InvalidCredentialsException;
import com.lenscart.exception.UserNotFoundException;
import com.lenscart.feign.FramesFeignClient;
import com.lenscart.feign.GlassFeignClient;
import com.lenscart.feign.LensesFeignClient;
import com.lenscart.feign.OrderServiceClient;
import com.lenscart.feign.ProductServiceClient;
import com.lenscart.feign.SunGlassFeignClient;
import com.lenscart.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private FramesFeignClient framesFeignClient;

    @Autowired
    private LensesFeignClient lensesFeignClient;
    
    @Autowired
    private GlassFeignClient glassFeignClient;
    
    @Autowired
    private SunGlassFeignClient sunglassFeignClient;

    private final AdminRepository adminRepository;
    private final ProductServiceClient productServiceClient;
    private final OrderServiceClient orderServiceClient;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    public AdminServiceImpl(AdminRepository adminRepository, ProductServiceClient productServiceClient,
                            OrderServiceClient orderServiceClient, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.productServiceClient = productServiceClient;
        this.orderServiceClient = orderServiceClient;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean loginAdmin(String email, String password) {
        logger.info("Admin login attempt: {}", email);
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isEmpty()) {
            logger.error("Admin not found: {}", email);
            throw new UserNotFoundException("Admin not found with email: " + email);
        }

        Admin admin = adminOptional.get();
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            logger.error("Invalid credentials for admin email: {}", email);
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return true;
    }


    // ✅ Add Frame
    @Override
    public FramesDTO addFrame(FramesDTO framesDTO) {
        logger.info("Admin adding new Frame: {}", framesDTO.getBrand());
        return framesFeignClient.addFrame(framesDTO);
    }

    // ✅ Add Lens
    @Override
    public LensesDTO addLens(LensesDTO lensesDTO) {
        logger.info("Admin adding new Lens: {}", lensesDTO.getBrand());
        return lensesFeignClient.addLens(lensesDTO);
    }

    // ✅ Update Frame
    @Override
    public FramesDTO updateFrame(String frameId, FramesDTO framesDTO) {
        logger.info("Admin updating Frame: {}", frameId);
        return framesFeignClient.updateFrame(frameId, framesDTO);
    }

    // ✅ Update Lens
    @Override
    public LensesDTO updateLens(String lensId, LensesDTO lensesDTO) {
        logger.info("Admin updating Lens: {}", lensId);
        return lensesFeignClient.updateLens(lensId, lensesDTO);
    }

    // ✅ Delete Frame
    @Override
    public void deleteFrame(String frameId) {
        logger.info("Admin deleting Frame: {}", frameId);
         framesFeignClient.deleteFrame(frameId);
    }

    // ✅ Delete Lens
    @Override
    public boolean deleteLens(String lensId) {
        logger.info("Admin deleting Lens: {}", lensId);
        return lensesFeignClient.deleteLens(lensId);
    }
    
    // ✅ Add Glass
    @Override
    public GlassDTO addGlass(GlassDTO glassDTO) {
        logger.info("Admin adding new Glass: {}", glassDTO.getBrand());
        return glassFeignClient.addGlass(glassDTO);
    }
    
    // ✅ Update Glass
    @Override
    public GlassDTO updateGlass(String glassId, GlassDTO glassDTO) {
        logger.info("Admin updating Lens: {}", glassId);
        return glassFeignClient.updateGlass(glassId, glassDTO);
    }
    
    // ✅ Delete Glass
    @Override
    public void  deleteGlass(String glassId) {
        logger.info("Admin deleting Glass: {}", glassId);
         glassFeignClient.deleteGlass(glassId);
    }
    
    
    
    // ✅ Add Glass
    @Override
    public SunGlassDTO addSunGlass(SunGlassDTO sunglassDTO) {
        logger.info("Admin adding new SunGlass: {}", sunglassDTO.getBrand());
        return sunglassFeignClient.addSunGlass(sunglassDTO);
    }
    
    // ✅ Update Glass
    @Override
    public SunGlassDTO updateSunGlass(String sunglassId, SunGlassDTO sunglassDTO) {
        logger.info("Admin updating SunGlass: {}", sunglassId);
        return sunglassFeignClient.updateSunGlass(sunglassId, sunglassDTO);
    }
    
    // ✅ Delete Glass
    @Override
    public void  deleteSunGlass(String sunglassId) {
        logger.info("Admin deleting SunGlass: {}", sunglassId);
         sunglassFeignClient.deleteSunGlass(sunglassId);
    }

    @Override
    public String updateOrder(Long orderId, String status) {
        logger.info("Admin updating order ID: {} with status: {}", orderId, status);
        return orderServiceClient.updateOrder(orderId, status);
    }
}
