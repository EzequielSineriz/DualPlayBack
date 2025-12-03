package com.DualPlay.controllers;

import com.DualPlay.dtos.request.BoardGameReqDTO;
import com.DualPlay.dtos.response.BoardGameRespDTO;
import com.DualPlay.service.BoardGameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boardgames")
@RequiredArgsConstructor
public class BoardGameController {

    private final BoardGameService boardGameService;

    @PostMapping
    public ResponseEntity<BoardGameRespDTO> create(@Valid @RequestBody BoardGameReqDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(boardGameService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardGameRespDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(boardGameService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BoardGameRespDTO>> getAll() {
        return ResponseEntity.ok(boardGameService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardGameRespDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody BoardGameReqDTO dto
    ) {
        return ResponseEntity.ok(boardGameService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardGameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

