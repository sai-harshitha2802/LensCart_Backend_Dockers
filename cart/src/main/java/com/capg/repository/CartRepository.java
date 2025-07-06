package com.capg.repository;

import com.capg.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

	Optional<Cart> findByCustomerId(Long customerId);
//	@Query("SELECT SUM(ci.quantity) FROM CartItem ci WHERE ci.productId = :productId")
//	Integer findTotalQuantityForProduct(@Param("productId") String productId);

}
