package com.DualPlay.dtos;

public record ProductResponseDTO(
     String name,
     String description,
     Double  price,
     Integer stock,
     String imageUrl

) {
}
