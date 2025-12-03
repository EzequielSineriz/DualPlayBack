package com.DualPlay.dtos.request;


public record ProductReqDTO(


        String name,
        String description,
        Double  price,
        Integer stock,
        String imageUrl,
        String type
) {
}
