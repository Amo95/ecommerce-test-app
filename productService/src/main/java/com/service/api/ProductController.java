package com.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.dto.ProductRequest;
import com.service.dto.ProductResponse;
import com.service.model.Product;
import com.service.service.mapper.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping("/add/restaurant/")
    @Operation(summary = "add product to db", description = "add product to db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added product"),
            @ApiResponse(responseCode = "404", description = "page not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ProductResponse> addProduct(@RequestBody @Valid ProductRequest request){
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @PostMapping("/add/restaurants/")
    @Operation(summary = "add list of products to db", description = "add list of products to db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added products"),
            @ApiResponse(responseCode = "404", description = "page not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ProductResponse>> addProductList(@RequestBody List<ProductRequest> requests){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProductList(requests));
    }

    @GetMapping("/products/getAll")
    public ResponseEntity<List<ProductResponse>> getAllProduct () {
        return ResponseEntity.ok(productService.fetchAllProducts());
    }

    @GetMapping("/sendToCart/{id}")
    @Operation(summary = "send product to cart", description = "publish product to cart via message broker")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added products"),
            @ApiResponse(responseCode = "404", description = "page not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Product> sendToCart(@PathVariable long id) throws JsonProcessingException {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
