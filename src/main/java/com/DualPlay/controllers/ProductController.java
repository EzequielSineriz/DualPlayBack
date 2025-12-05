package com.DualPlay.controllers;


import com.DualPlay.dtos.request.StockRequest;
import com.DualPlay.dtos.response.ProductResponseDTO;
import com.DualPlay.entities.TypeOfProduct;
import com.DualPlay.service.ImageService;
import com.DualPlay.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByType(@PathVariable TypeOfProduct type) {
        return ResponseEntity.ok(productService.findByType(type));
    }

    @PatchMapping("/{id}/stock/reduce")
    public ResponseEntity<Void> reduceStock(
            @PathVariable Long id,
            @RequestParam int amount) {

        productService.reduceStock(id, amount);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(
            @PathVariable Long id,
            @RequestBody StockRequest request) {

        productService.updateStock(id, request.getStock());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/stock/increase")
    public ResponseEntity<Void> increaseStock(
            @PathVariable Long id,
            @RequestParam int amount) {

        productService.increaseStock(id, amount);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<Void> disable(@PathVariable Long id) {
        productService.disable(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/enable")
    public ResponseEntity<Void> enable(@PathVariable Long id) {
        productService.enable(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<?> updateImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            String imageUrl = imageService.saveImage(file);
            productService.updateImage(id, imageUrl);

            return ResponseEntity.ok(imageUrl);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }



}
