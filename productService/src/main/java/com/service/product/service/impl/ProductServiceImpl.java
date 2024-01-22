package com.service.product.service.impl;

import com.service.product.dto.ProductRequest;
import com.service.product.dto.ProductResponse;
import com.service.product.exceptions.NotFoundException;
import com.service.product.model.Product;
import com.service.product.repository.ProductRepository;
import com.service.product.service.ProductService;
import com.service.product.util.BasicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BasicMapper basicMapper;

    @Override
    public ProductResponse addProduct(ProductRequest request) {
        Product product = basicMapper.convertTo(request, Product.class);
        return basicMapper.convertTo(addRestaurant(request), ProductResponse.class);
    }

    private Product addRestaurant(ProductRequest request) {

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
        return productRepository.save(product);
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Shipping address not found"));
    }
}
