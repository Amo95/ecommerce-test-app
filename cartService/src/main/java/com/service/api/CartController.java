package com.service.api;

import com.service.model.Product;
import com.service.service.mapper.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "fetch cart items from db", description = "fetch all items in cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched items from cart"),
            @ApiResponse(responseCode = "404", description = "page not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Product>> getCartItems(){
        return ResponseEntity.ok(productService.getCartItem());
    }

    @GetMapping("/delete/product/{id}")
    @Operation(summary = "remove cart item from db", description = "remove product from cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removed product from cart"),
            @ApiResponse(responseCode = "404", description = "page not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/deleteAll")
    @Operation(summary = "empty cart", description = "remove cart items from db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removed items from cart"),
            @ApiResponse(responseCode = "404", description = "page not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteProducts() {
        productService.deleteAllItems();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok(productService.getInfo());
    }
}
