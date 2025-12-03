package com.DualPlay.mappers;


import com.DualPlay.dtos.request.ProductReqDTO;
import com.DualPlay.dtos.response.ProductResponseDTO;
import com.DualPlay.entities.Product;
import com.DualPlay.entities.TypeOfProduct;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl(),
                product.getType().name()
        );
    }

    public void updateProductFromReq(Product product, ProductReqDTO dto) {
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setImageUrl(dto.imageUrl());
        product.setType(TypeOfProduct.valueOf(dto.type()));
    }
}
