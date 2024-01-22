package com.service.product.service;

import com.service.product.dto.ProductRequest;
import com.service.product.dto.ProductResponse;

public interface ProductService {
    public ProductResponse addProduct(ProductRequest request);
}
