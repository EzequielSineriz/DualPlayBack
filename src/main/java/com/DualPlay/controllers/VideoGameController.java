package com.DualPlay.controllers;


import com.DualPlay.dtos.request.VideoGameReqDTO;
import com.DualPlay.dtos.response.VideoGameRespDTO;
import com.DualPlay.service.VideoGameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videogames")
@RequiredArgsConstructor
public class VideoGameController {

    private final VideoGameService videoGameService;

    @PostMapping
    public ResponseEntity<VideoGameRespDTO> create(@Valid @RequestBody VideoGameReqDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(videoGameService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoGameRespDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(videoGameService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VideoGameRespDTO>> getAll() {
        return ResponseEntity.ok(videoGameService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoGameRespDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody VideoGameReqDTO dto
    ) {
        return ResponseEntity.ok(videoGameService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        videoGameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
