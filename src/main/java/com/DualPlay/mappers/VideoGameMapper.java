package com.DualPlay.mappers;

import com.DualPlay.dtos.request.VideoGameReqDTO;
import com.DualPlay.dtos.response.SaleItemRespDTO;
import com.DualPlay.dtos.response.VideoGameRespDTO;
import com.DualPlay.entities.SaleItem;
import com.DualPlay.entities.TypeOfProduct;
import com.DualPlay.entities.VideoGame;
import org.springframework.stereotype.Component;

@Component
public class VideoGameMapper {

    public VideoGame toEntity(VideoGameReqDTO dto) {
        VideoGame game = new VideoGame();

        game.setName(dto.name());
        game.setDescription(dto.description());
        game.setPrice(dto.price());
        game.setStock(dto.stock());
        game.setImageUrl(dto.imageUrl());
        game.setPlatform(dto.platform());
        game.setGenre(dto.genre());

        // IMPORTANTE: siempre setear el type del enum
        game.setType(TypeOfProduct.VIDEOGAME);

        return game;
    }

    public VideoGameRespDTO toVideoGameRespDTO(VideoGame game){

        return new VideoGameRespDTO(
                game.getId(),
                game.getName(),
                game.getDescription(),
                game.getPrice(),
                game.getStock(),
                game.getImageUrl(),
                game.getPlatform(),
                game.getGenre()

        );
    }
}
