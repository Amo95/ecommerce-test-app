package com.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.dto.ProductRequest;
import com.service.dto.ProductResponse;
import com.service.model.Product;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest request);

    List<ProductResponse> addProductList(List<ProductRequest> requests);

    List<ProductResponse> fetchAllProducts();

    Product getProductById(long id) throws JsonProcessingException;
}
