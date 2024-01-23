package com.service.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.product.dto.ProductRequest;
import com.service.product.dto.ProductResponse;
import com.service.product.model.Product;

import java.util.List;

public interface ProductService {
    public ProductResponse addProduct(ProductRequest request);

    List<ProductResponse> addProductList(List<ProductRequest> requests);

    List<ProductResponse> fetchAllProducts();

    Product getProductById(long id) throws JsonProcessingException;
}
