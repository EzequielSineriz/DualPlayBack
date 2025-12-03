package com.DualPlay.service;


import com.DualPlay.dtos.request.BoardGameReqDTO;
import com.DualPlay.dtos.response.BoardGameRespDTO;
import com.DualPlay.entities.BoardGame;
import com.DualPlay.entities.TypeOfProduct;
import com.DualPlay.mappers.BoardGameMapper;
import com.DualPlay.repository.BoardGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardGameService {

    private final BoardGameRepository boardGameRepository;
    private final BoardGameMapper boardGameMapper;

    // CREATE
    public BoardGameRespDTO create(BoardGameReqDTO dto) {
        BoardGame game = boardGameMapper.toEntity(dto);
        boardGameRepository.save(game);
        return boardGameMapper.toRespDTO(game);
    }

    // GET BY ID
    public BoardGameRespDTO getById(Long id) {
        BoardGame game = boardGameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BoardGame not found"));

        return boardGameMapper.toRespDTO(game);
    }



    // GET ALL
    public List<BoardGameRespDTO> getAll() {
        return boardGameRepository.findAll()
                .stream()
                .map(boardGameMapper::toRespDTO)
                .toList();
    }

    // UPDATE
    public BoardGameRespDTO update(Long id, BoardGameReqDTO dto) {
        BoardGame game = boardGameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BoardGame not found"));

        game.setName(dto.name());
        game.setDescription(dto.description());
        game.setPrice(dto.price());
        game.setStock(dto.stock());
        game.setImageUrl(dto.imageUrl());

        game.setMinPlayers(dto.minPlayers());
        game.setMaxPlayers(dto.maxPlayers());
        game.setRecommendedAge(dto.recommendedAge());

        game.setType(TypeOfProduct.BOARDGAME);

        boardGameRepository.save(game);

        return boardGameMapper.toRespDTO(game);
    }

    // DELETE
    public void delete(Long id) {
        if (!boardGameRepository.existsById(id)) {
            throw new RuntimeException("BoardGame not found");
        }
        boardGameRepository.deleteById(id);
    }


}
