package com.DualPlay.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoardGameRespDTO (
        @NotNull(message = "the id cannot be null")
        Long id,

        @NotBlank(message = "the name cannot be blank.")
        String name,
        @NotBlank(message = "the product must have a description.")
        String description,
        @Min(value = 1)
        @Max(value = 1000000)
        Double price,
        Integer stock,
        String imageUrl,
        Integer minPlayers,
        Integer maxPlayers,
        Integer recommendedAge,
        String type
) {
}
