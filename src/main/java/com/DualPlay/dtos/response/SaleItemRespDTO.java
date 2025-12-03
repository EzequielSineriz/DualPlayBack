package com.DualPlay.dtos.response;

public record SaleItemRespDTO(
        Long id,
        Long productId,
        Double unitPrice,
        Integer quantity,
        Double subtotal
) {
}
