package com.DualPlay.dtos;

public record BoardGameReqDTO (
        String name,
        String description,
        Double price,
        Integer stock,
        String imageUrl,
        Integer minPlayers,
        Integer maxPlayers,
        Integer recommendedAge
){}