package com.DualPlay.mappers;


import com.DualPlay.dtos.request.BoardGameReqDTO;
import com.DualPlay.dtos.response.BoardGameRespDTO;
import com.DualPlay.entities.BoardGame;
import com.DualPlay.entities.TypeOfProduct;
import org.springframework.stereotype.Component;

@Component
public class BoardGameMapper {

    public BoardGame toEntity(BoardGameReqDTO dto){
        BoardGame game = new BoardGame();

        game.setName(dto.name());
        game.setDescription(dto.description());
        game.setPrice(dto.price());
        game.setStock(dto.stock());
        game.setImageUrl(dto.imageUrl());

        game.setMinPlayers(dto.minPlayers());
        game.setMaxPlayers(dto.maxPlayers());
        game.setRecommendedAge(dto.recommendedAge());

        game.setType(TypeOfProduct.BOARDGAME);

        return game;
    }

    public BoardGameRespDTO toRespDTO(BoardGame game){
        return new BoardGameRespDTO(
                game.getId(),
                game.getName(),
                game.getDescription(),
                game.getPrice(),
                game.getStock(),
                game.getImageUrl(),
                game.getMinPlayers(),
                game.getMaxPlayers(),
                game.getRecommendedAge()
        );
    }
}
