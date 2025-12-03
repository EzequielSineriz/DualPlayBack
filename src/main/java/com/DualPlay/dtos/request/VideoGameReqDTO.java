package com.DualPlay.dtos.request;

import jakarta.validation.constraints.*;

public record VideoGameReqDTO(

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


        @NotBlank(message = "The image must have a path")
        @Size(min = 2, max = 20)
        String imageUrl,

        @NotBlank(message = "It must belong to a valid platform")
        @Size(min = 2, max = 50, message = "It must have a minimum length of between 2 and 50 characters.")
        String platform,

        @NotBlank(message = "It must have a valid genre.")
        String genre,

        String type
) {
}
