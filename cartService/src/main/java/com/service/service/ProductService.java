package com.service.service;

import com.service.model.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {

    List<Product> getCartItem();
    void deleteCartItem(@PathVariable Long id);
    void deleteAllItems();
    String getInfo();
}
