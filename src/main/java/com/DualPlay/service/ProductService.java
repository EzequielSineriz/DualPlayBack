package com.DualPlay.service;

import com.DualPlay.entities.Product;
import com.DualPlay.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void reduceStock(Long productId, int amount) {
        Product product = findById(productId);
        product.setStock(product.getStock() - amount);
        productRepository.save(product);
    }

    public void increaseStock(Long productId, int amount) {
        Product product = findById(productId);
        product.setStock(product.getStock() + amount);
        productRepository.save(product);
    }
}
