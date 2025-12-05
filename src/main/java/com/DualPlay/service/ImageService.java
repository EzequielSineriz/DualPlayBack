package com.DualPlay.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final String uploadDir = "src/main/resources/static/uploads/";

    public String saveImage(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IOException("El archivo está vacío");
        }

        // Crear carpeta si no existe
        Path folder = Paths.get(uploadDir);
        if (!Files.exists(folder)) {
            Files.createDirectories(folder);
        }

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName = UUID.randomUUID() + extension;

        Path filePath = folder.resolve(newFileName);
        Files.write(filePath, file.getBytes());

        // URL pública
        return "/uploads/" + newFileName;
    }
}
