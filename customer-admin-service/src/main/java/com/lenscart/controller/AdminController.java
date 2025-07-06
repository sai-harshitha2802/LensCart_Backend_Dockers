package com.lenscart.controller;

import com.lenscart.dto.OrderDTO;
import com.lenscart.dto.FramesDTO;
import com.lenscart.dto.GlassDTO;
import com.lenscart.dto.LensesDTO;

import com.lenscart.dto.SunGlassDTO;
import com.lenscart.feign.OrderServiceClient;
import com.lenscart.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@Tag(name = "Admin Controller", description = "Handles admin-related operations")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private final AdminService adminService;
	private final OrderServiceClient orderService;

	public AdminController(AdminService adminService,OrderServiceClient orderService) {
		this.adminService = adminService;
		this.orderService=orderService;
	}

	// ✅ Add Frame
	@Operation(summary = "Add a new frame")
	@PostMapping("/frame/add")
	public ResponseEntity<FramesDTO> addFrame(@RequestBody FramesDTO framesDTO) {
		return ResponseEntity.ok(adminService.addFrame(framesDTO));
	}

	// ✅ Add Lens
	@Operation(summary = "Add a new lens")
	@PostMapping("/lens/add")
	public ResponseEntity<LensesDTO> addLens(@RequestBody LensesDTO lensesDTO) {
		return ResponseEntity.ok(adminService.addLens(lensesDTO));
	}

	// ✅ Add Glass
	@Operation(summary = "Add a new glass")
	@PostMapping("/glass/add")
	public ResponseEntity<GlassDTO> addGlass(@RequestBody GlassDTO glassDTO) {
		return ResponseEntity.ok(adminService.addGlass(glassDTO));
	}

	// ✅ Add SunGlass
	@Operation(summary = "Add a new sunglass")
	@PostMapping("/sunglass/add")
	public ResponseEntity<SunGlassDTO> addSunGlass(@RequestBody SunGlassDTO sunglassDTO) {
		return ResponseEntity.ok(adminService.addSunGlass(sunglassDTO));
	}

	// ✅ Update Frame
	@Operation(summary = "Update an existing frame")
	@PutMapping("/frame/update/{frameId}")
	public ResponseEntity<FramesDTO> updateFrame(@PathVariable String frameId, @RequestBody FramesDTO framesDTO) {
		return ResponseEntity.ok(adminService.updateFrame(frameId, framesDTO));
	}

	// ✅ Update Lens
	@Operation(summary = "Update an existing lens")
	@PutMapping("/lens/update/{lensId}")
	public ResponseEntity<LensesDTO> updateLens(@PathVariable String lensId, @RequestBody LensesDTO lensesDTO) {
		return ResponseEntity.ok(adminService.updateLens(lensId, lensesDTO));
	}

	// ✅ Update Glass
	@Operation(summary = "Update an existing glass")
	@PutMapping("/glass/update/{glassId}")
	public ResponseEntity<GlassDTO> updateGlass(@PathVariable String glassId, @RequestBody GlassDTO glassDTO) {
		return ResponseEntity.ok(adminService.updateGlass(glassId, glassDTO));
	}

	// ✅ Update SunGlass
	@Operation(summary = "Update an existing sunglass")
	@PutMapping("/sunglass/update/{sunglassId}")
	public ResponseEntity<SunGlassDTO> updateSunGlass(@PathVariable String sunglassId,
			@RequestBody SunGlassDTO sunglassDTO) {
		return ResponseEntity.ok(adminService.updateSunGlass(sunglassId, sunglassDTO));
	}

	// ✅ Delete Frame
	@Operation(summary = "Delete a frame by ID")
	@DeleteMapping("/frame/delete/{frameId}")
	public ResponseEntity<String> deleteFrame(@PathVariable String frameId) {
		try {
			adminService.deleteFrame(frameId);
			return ResponseEntity.ok("Frame deleted successfully");
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

	// ✅ Delete Lens
	@Operation(summary = "Delete a lens by ID")
	@DeleteMapping("/lens/delete/{lensId}")
	public ResponseEntity<String> deleteLens(@PathVariable String lensId) {
		try {
			adminService.deleteLens(lensId);
			return ResponseEntity.ok("Lens deleted successfully");
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

	// ✅ Delete Glass
	@Operation(summary = "Delete a lens by ID")
	@DeleteMapping("/glass/delete/{glassId}")
	public ResponseEntity<String> deleteGlass(@PathVariable String glassId) {
		try {
			adminService.deleteGlass(glassId);
			return ResponseEntity.ok("Lens deleted successfully");
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

	// ✅ Delete SunGlass
	@Operation(summary = "Delete a sunglass by ID")
	@DeleteMapping("/sunglass/delete/{sunglassId}")
	public ResponseEntity<String> deleteSunGlass(@PathVariable String sunglassId) {
		try {
			adminService.deleteSunGlass(sunglassId);
			return ResponseEntity.ok("Sunglass deleted successfully");
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

	// ✅ Update Orders
	@Operation(summary = "Update an order (Feign Client call)")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/orders/{orderId}")
	public ResponseEntity<String> updateOrder(@PathVariable Long orderId, @RequestParam String status) {
		logger.info("Admin updating order ID: {} with status: {}", orderId, status);
		String response = adminService.updateOrder(orderId, status);
		return ResponseEntity.ok(response);
	}
@GetMapping("/order/all")
    
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
	
	
}
