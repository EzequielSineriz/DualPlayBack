package com.DualPlay.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

public record SaleRespDTO(

        Long id,
        LocalDateTime date,
        Long customerId,
        String customerName,
        Double amount,
        List<SaleItemRespDTO> items
) {
}
