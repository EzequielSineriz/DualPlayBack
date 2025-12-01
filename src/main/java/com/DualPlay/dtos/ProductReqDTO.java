package com.DualPlay.dtos;


public record ProductReqDTO(

        String name,
        String description,
        Double  price,
        Integer stock,
        String imageUrl
) {
}
