package com.DualPlay.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "board_games")
public class BoardGame extends Product{

    private Integer minPlayers;
    private Integer maxPlayers;
    private Integer recommendedAge;

}
