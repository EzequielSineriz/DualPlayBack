package com.DualPlay.repository;

import com.DualPlay.entities.Product;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(@NotNull(message = "Product ID is reqired.") Long aLong);
}
