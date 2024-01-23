package com.service.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.commons.utils.BasicMapper;
import com.service.dto.ProductRequest;
import com.service.dto.ProductResponse;
import com.service.exceptions.NotFoundException;
import com.service.model.Product;
import com.service.repository.ProductRepository;
import com.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BasicMapper basicMapper;
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper mapper;
    @Value("${product.jms.destination}")
    private String jmsQueue;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public ProductResponse addProduct(ProductRequest request) {
        Product product = basicMapper.convertTo(request, Product.class);
        return basicMapper.convertTo(addProductData(product), ProductResponse.class);
    }

    @Override
    public List<ProductResponse> addProductList(List<ProductRequest> requests) {
        List<Product> products = basicMapper.convertListTo(requests, Product.class);
        return basicMapper.convertListTo(productRepository.saveAll(products), ProductResponse.class);
    }

    @Override
    public List<ProductResponse> fetchAllProducts() {
        return basicMapper.convertListTo(getAllProducts(), ProductResponse.class);
    }

    @Override
    public Product getProductById(long id) throws JsonProcessingException {
        Product product = getProduct(id);
        String jsonInString = mapper.writeValueAsString(product);
        LOGGER.info(String.format("send product data to cart => %s", jsonInString));
        jmsTemplate.convertAndSend(jmsQueue, jsonInString);

        return product;
    }

    private List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    private Product addProductData(Product product) {

        Product productData = Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
        return productRepository.save(productData);
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("product not found"));
    }
}
