package com.DualPlay.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SaleReqDTO(

        @NotNull(message = "Customer Id is required")
        Long customerId,

        @NotNull(message = "The sale must contain at least one item.")
        @Size(min = 1, message = "The sale must contain at least one item.")
        List<SaleItemReqDTO> items
) {
}
