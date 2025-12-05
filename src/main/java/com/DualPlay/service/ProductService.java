package com.DualPlay.service;

import com.DualPlay.dtos.request.StockRequest;
import com.DualPlay.dtos.response.ProductResponseDTO;
import com.DualPlay.entities.Product;
import com.DualPlay.entities.TypeOfProduct;
import com.DualPlay.mappers.ProductMapper;
import com.DualPlay.repository.BoardGameRepository;
import com.DualPlay.repository.ProductRepository;
import com.DualPlay.repository.VideoGameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final VideoGameRepository videoGameRepository;

    private final BoardGameRepository boardGameRepository;


    public Product findByIdEntity(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
    }

    public ProductResponseDTO findById(Long id) {
        Product product = findByIdEntity(id);
        return productMapper.toProductResponseDTO(findByIdEntity(id));
    }

    public List<ProductResponseDTO> findAll() {

        return productRepository.findAll().stream()
                .filter(Product::isActive)
                .map(productMapper::toProductResponseDTO)

                .toList();
    }

    public List<ProductResponseDTO> findByType(TypeOfProduct type) {
        List<? extends Product> products;

        switch (type) {
            case VIDEOGAME -> products = videoGameRepository.findAll();
            case BOARDGAME -> products = boardGameRepository.findAll();
            default -> throw new IllegalArgumentException("Invalid product type");
        }

        return products.stream()
                .filter(Product::isActive)
                .map(productMapper::toProductResponseDTO)
                .toList();
    }
    public void reduceStock(Long id, int amount) {
        Product product = findByIdEntity(id);
        product.setStock(product.getStock() - amount);
        validateActive(product);
        productRepository.save(product);
    }

    public void increaseStock(Long id, int amount) {
        Product product = findByIdEntity(id);
        product.setStock(product.getStock() + amount);
        validateActive(product);
        productRepository.save(product);
    }

    private void validateActive(Product product) {
        product.setActive(product.getStock() > 0);
    }

    public void updateStock(Long id, Integer newStock) {
        Product product = findByIdEntity(id);

        if (newStock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        product.setStock(newStock);

        // 🔥 Política: si stock = 0 → inactive. si stock > 0 → active
        product.setActive(newStock > 0);

        productRepository.save(product);
    }

    // Para deshabilitar manualmente desde el panel
    public void disable(Long id) {
        Product p = findByIdEntity(id);
        p.setActive(false);
        productRepository.save(p);
    }

    // Para habilitar manualmente desde el panel
    public void enable(Long id) {
        Product p = findByIdEntity(id);
        p.setActive(true);
        productRepository.save(p);
    }

    public void updateImage(Long id, String imageUrl) {
        Product p = findByIdEntity(id);
        p.setImageUrl(imageUrl);
        productRepository.save(p);
    }


}
