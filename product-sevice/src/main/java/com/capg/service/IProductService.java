package com.capg.service;
import java.util.List;
import com.capg.dto.ProductDTO;

public interface IProductService {
	List<ProductDTO> getAllProducts();
    ProductDTO getProductById(String id);

}
