package com.DualPlay.dtos.response;

public record BoardGameRespDTO (
        Long id,
        String name,
        String description,
        Double price,
        Integer stock,
        String imageUrl,
        Integer minPlayers,
        Integer maxPlayers,
        Integer recommendedAge
) {}
