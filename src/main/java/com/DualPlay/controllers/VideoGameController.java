package com.DualPlay.controllers;


import com.DualPlay.dtos.request.VideoGameReqDTO;
import com.DualPlay.dtos.response.VideoGameRespDTO;
import com.DualPlay.service.VideoGameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    // Endpoint para crear VideoGame con imagen
    @PostMapping("/upload")
    public ResponseEntity<VideoGameRespDTO> createWithImage(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam int stock,
            @RequestParam String platform,
            @RequestParam String genre,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
            // 1. Guardamos la imagen PRIMERO para obtener la URL
        String imageUrl = videoGameService.saveImage(image);
        String type = "VIDEOGAME";

        // 2. Creamos el Record VideoGameReqDTO usando el constructor canónico,
        //    pasando la imageUrl que acabamos de obtener.
        VideoGameReqDTO dto = new VideoGameReqDTO(
                name,
                description,
                price,
                stock,
                imageUrl,
                platform,
                genre,// El campo del Record se inicializa aquí
                type
        );

        // 3. Procedemos con la lógica de negocio
        VideoGameRespDTO response = videoGameService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

