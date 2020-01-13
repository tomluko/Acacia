package com.tomluk.acacia.entities.components.spawner;

import com.tomluk.acacia.GameBoard;
import com.tomluk.acacia.entities.EntityFactory;
import com.tomluk.entities.ComponentAdapter;
import com.tomluk.entities.Entities;
import com.tomluk.entities.Entity;

public class SpawnComponent extends ComponentAdapter<Object> {

    @Override
    public void update(Entity<Object> entity, double time) {
        EntityFactory.createSnake(GameBoard.Instance.getMiddlePosition());
        EntityFactory.createFood(GameBoard.Instance.getRandomEmptyPosition());
        Entities.Instance.removeEntity(entity);
    }
}
