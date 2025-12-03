package com.DualPlay.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SaleItemReqDTO(
        @NotNull(message = "Product ID is reqired.")
        Long productId,

        @NotNull(message = "Quantity is required")
        @Min(value = 1, message = "Quantity must be at least 1.")
        @Max(value = 30, message = "Maximum quantity 30, for wholesale purchases contact the company via WhatsApp")
        Integer quantity
) {
}
