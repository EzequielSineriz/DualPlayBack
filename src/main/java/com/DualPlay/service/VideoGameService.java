package com.DualPlay.service;

import com.DualPlay.dtos.request.VideoGameReqDTO;
import com.DualPlay.dtos.response.VideoGameRespDTO;
import com.DualPlay.entities.TypeOfProduct;
import com.DualPlay.entities.VideoGame;
import com.DualPlay.mappers.VideoGameMapper;
import com.DualPlay.repository.VideoGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoGameService {

    private final VideoGameRepository videoGameRepository;

    private final VideoGameMapper videoGameMapper;

    private final String uploadDir = "uploads/videogames"; // Carpeta donde se guardan las imágenes

    // Metodo para guardar la imagen en disco
    public String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) return null;

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Retornamos URL relativa
        return "/uploads/videogames/" + filename;
    }
    //CREATE
    public VideoGameRespDTO create(VideoGameReqDTO dto){
        VideoGame game = videoGameMapper.toEntity(dto);
        videoGameRepository.save(game);
        return videoGameMapper.toVideoGameRespDTO(game);
    }

    public VideoGameRespDTO getById(Long id){
        VideoGame game = videoGameRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("VideoGame not found"));

        return videoGameMapper.toVideoGameRespDTO(game);
    }

    public List<VideoGameRespDTO> getAll(){
        return videoGameRepository.findAll()
                .stream()
                .map(videoGameMapper::toVideoGameRespDTO)
                .toList();
    }

    public VideoGameRespDTO update(Long id, VideoGameReqDTO dto) {
        VideoGame game = videoGameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VideoGame not found"));

        game.setName(dto.name());
        game.setDescription(dto.description());
        game.setPrice(dto.price());
        game.setStock(dto.stock());
        game.setImageUrl(dto.imageUrl());
        game.setPlatform(dto.platform());
        game.setGenre(dto.genre());
        game.setType(TypeOfProduct.VIDEOGAME); // siempre

        videoGameRepository.save(game);

        return videoGameMapper.toVideoGameRespDTO(game);
    }

    public void delete(Long id){
        if(!videoGameRepository.existsById(id)){
            throw new RuntimeException("VideoGame Not Found");
        }
    }
}
