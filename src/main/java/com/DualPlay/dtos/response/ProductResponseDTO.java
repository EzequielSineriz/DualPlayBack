package com.DualPlay.dtos.response;

public record ProductResponseDTO(
     Long id,
     String name,
     String description,
     Double  price,
     Integer stock,
     String imageUrl,
     String type

) {
}
