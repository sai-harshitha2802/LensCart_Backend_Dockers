package com.project1.service;
import java.util.List;

import com.project1.dto.ProductDTO;

public interface IProductService {
	List<ProductDTO> getAllProducts();
    ProductDTO getProductById(String id);

}
