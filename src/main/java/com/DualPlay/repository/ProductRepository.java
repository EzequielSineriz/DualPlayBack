package com.DualPlay.repository;

import com.DualPlay.dtos.response.ProductResponseDTO;
import com.DualPlay.entities.Product;
import com.DualPlay.entities.TypeOfProduct;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(@NotNull(message = "Product ID is reqired.") Long aLong);

    List<ProductResponseDTO> findByType(TypeOfProduct type);
}
