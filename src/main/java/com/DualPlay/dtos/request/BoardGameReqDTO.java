package com.DualPlay.dtos.request;

import jakarta.validation.constraints.*;

public record BoardGameReqDTO (

        @NotBlank(message = "The name cannot be blank.")
        @Size(min = 3, max = 100, message = "It must be between 3 and 30 characters long")
        String name,

        @NotBlank(message = "The product must have a description.")
        String description,

        @NotNull(message = "the price is mandatory")
        @Min(value = 1, message = "At a minimum, the price value must be 1")
        @Max(value = 1000000, message = "Cannot exceed the maximum value of 100000")
        Double price,

        @NotNull(message = "It must have valid stock")
        @Min(value = 0, message = "The stock must be equal to or greater than zero")
        @Max(value = 100000, message = "The stock must be less than 100000 ")
        Integer stock,

        @Size(min = 2, max = 255)
        @NotBlank(message = "The image must have a path")
        String imageUrl,

        @Min(value = 1, message = "There must be at least one player")
        @Max(value = 6, message = "There cannot be a minimum of more than 6 players")
        Integer minPlayers,


        @Min(value = 2, message = "There must be at least 2 players")
        @Max(value = 20, message = "There must be a maximum of 20 players")
        Integer maxPlayers,

        @Min(value = 4, message = "Must be at least 4 years old")
        @Max(value = 100, message = "It must be no more than 100 years old")
        Integer recommendedAge,

        String type


){}