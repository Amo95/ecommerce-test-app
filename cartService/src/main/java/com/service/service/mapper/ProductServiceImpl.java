package com.service.service.mapper;

import com.service.model.Product;
import com.service.repository.ProductRepository;
import com.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getCartItem() {
        return productRepository.findAll();
    }

    @Override
    public void deleteCartItem(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAllItems() {
        productRepository.deleteAll();
    }

    @Override
    public String getInfo() {
        return "cart microservice";
    }
}
