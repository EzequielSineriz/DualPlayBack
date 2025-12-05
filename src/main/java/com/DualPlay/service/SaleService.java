package com.DualPlay.service;

import com.DualPlay.dtos.request.SaleItemReqDTO;
import com.DualPlay.dtos.request.SaleReqDTO;
import com.DualPlay.dtos.response.SaleRespDTO;
import com.DualPlay.entities.Product;
import com.DualPlay.entities.Sale;
import com.DualPlay.entities.SaleItem;
import com.DualPlay.entities.User;
import com.DualPlay.mappers.SaleMapper;
import com.DualPlay.repository.ProductRepository;
import com.DualPlay.repository.SaleRepository;
import com.DualPlay.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    private final UserService userService;

    private final ProductRepository productRepository;

    private final ProductService productService;

    private final SaleMapper saleMapper;

    @Transactional
    public SaleRespDTO createSale(SaleReqDTO dto) {

        // 1. Buscar el Customer
        User customer = userService.findById(dto.customerId());

        // 2. Crear la venta sin guardar aún
        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setDate(LocalDateTime.now());
        sale.setItems(new ArrayList<>());

        // Necesario para usar dentro del stream
        Sale saleFinal = sale;

        double totalAmount = dto.items().stream().mapToDouble(itemDTO -> {

            Product product = productService.findByIdEntity(itemDTO.productId());

            if (product.getStock() < itemDTO.quantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            // Calcular subtotal
            double subtotal = product.getPrice() * itemDTO.quantity();

            // Descontar stock
            productService.reduceStock(product.getId(), itemDTO.quantity());

            // Crear SaleItem
            SaleItem item = new SaleItem();
            item.setSale(saleFinal);
            item.setProduct(product);
            item.setQuantity(itemDTO.quantity());
            item.setSubtotal(subtotal);

            saleFinal.getItems().add(item);

            return subtotal;

        }).sum();

        sale.setAmount(totalAmount);

        // Guardar venta con items
        saleRepository.save(sale);

        return saleMapper.toSaleRespDTO(sale);
    }

    public List<SaleRespDTO> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(saleMapper::toSaleRespDTO)
                .toList();
    }

    public SaleRespDTO getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        return saleMapper.toSaleRespDTO(sale);
    }
}