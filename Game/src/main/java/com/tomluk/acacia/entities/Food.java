package com.tomluk.acacia.entities;

import com.tomluk.acacia.GameBoard;
import com.tomluk.acacia.entities.gameobjects.GameBoardGameObject;
import com.tomluk.entities.Entities;
import com.tomluk.entities.Entity;

public class Food {

    public static void consume(Entity<GameBoardGameObject> foodEntity) {
        GameBoard.Instance.removeEntity(foodEntity.getGameObject().getPosition());
        Entities.Instance.removeEntity(foodEntity);
    }
}
