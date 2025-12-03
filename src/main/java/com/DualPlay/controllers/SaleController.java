package com.DualPlay.controllers;


import com.DualPlay.dtos.request.SaleReqDTO;
import com.DualPlay.dtos.response.SaleRespDTO;
import com.DualPlay.service.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;


    @PostMapping
    public ResponseEntity<SaleRespDTO> createSale(@RequestBody @Valid SaleReqDTO dto) {
        return ResponseEntity.ok(saleService.createSale(dto));
    }

    @GetMapping
    public ResponseEntity<List<SaleRespDTO>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleRespDTO> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }
}
