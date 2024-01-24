package com.service.api;

import com.service.model.Product;
import com.service.service.mapper.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final ProductServiceImpl productService;

    @GetMapping("/cartItems")
    public ResponseEntity<List<Product>> getCartItems(){
        return ResponseEntity.ok(productService.getCartItem());
    }

    @GetMapping("/deleteOne/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/deleteAll")
    public ResponseEntity<Void> deleteProducts() {
        productService.deleteAllItems();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok(productService.getInfo());
    }
}
