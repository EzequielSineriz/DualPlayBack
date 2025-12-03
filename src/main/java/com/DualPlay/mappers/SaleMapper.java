package com.DualPlay.mappers;

import com.DualPlay.dtos.response.SaleItemRespDTO;
import com.DualPlay.dtos.response.SaleRespDTO;
import com.DualPlay.entities.Sale;
import com.DualPlay.entities.SaleItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleMapper {
    public SaleItemRespDTO toSaleItemRespDTO(SaleItem item){
        return new SaleItemRespDTO(
                item.getId(),
                item.getProduct().getId(),
                item.getProduct().getPrice(),
                item.getQuantity(),
                item.getSubtotal()
        );
    }

    public SaleRespDTO toSaleRespDTO(Sale sale){
        List<SaleItemRespDTO> items = sale.getItems()
                .stream()
                .map(this::toSaleItemRespDTO)
                .toList();
        return new SaleRespDTO(
                sale.getId(),
                sale.getDate(),
                sale.getCustomer().getId(),
                sale.getCustomer().getUsername(),
                sale.getAmount(),
                items
        );
    }
}
