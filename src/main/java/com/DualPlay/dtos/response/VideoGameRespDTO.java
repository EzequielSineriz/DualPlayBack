package com.DualPlay.dtos.response;

public record VideoGameRespDTO(
        Long id,
        String name,
        String description,
        Double  price,
        Integer stock,
        String imageUrl,
        String platform,
        String genre

) {
}
