package com.service.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.model.Product;
import com.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerService {

    private final ProductRepository productRepository;
    private final ObjectMapper mapper;

    @JmsListener(destination = "${product.jms.destination}")
    public void consumeMessage(String eventMessage) {
        try {
            Product product = mapper.readValue(eventMessage, Product.class);
            productRepository.save(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
