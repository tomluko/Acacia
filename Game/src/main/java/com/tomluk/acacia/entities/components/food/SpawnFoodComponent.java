package com.tomluk.acacia.entities.components.food;

import com.tomluk.acacia.GameBoard;
import com.tomluk.acacia.entities.EntityFactory;
import com.tomluk.acacia.entities.gameobjects.GameBoardGameObject;
import com.tomluk.entities.ComponentAdapter;
import com.tomluk.entities.Entity;

public class SpawnFoodComponent extends ComponentAdapter<GameBoardGameObject> {

    @Override
    public void onRemove(Entity<GameBoardGameObject> entity) {
        EntityFactory.createFood(GameBoard.Instance.getRandomEmptyPosition());
    }
}
