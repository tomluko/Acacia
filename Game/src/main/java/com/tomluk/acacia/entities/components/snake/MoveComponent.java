package com.tomluk.acacia.entities.components.snake;

import com.tomluk.acacia.entities.Snake;
import com.tomluk.acacia.entities.gameobjects.GameBoardGameObject;
import com.tomluk.entities.ComponentAdapter;
import com.tomluk.entities.Entity;

import java.util.LinkedList;

public class MoveComponent extends ComponentAdapter<LinkedList<Entity<GameBoardGameObject>>> {

    @Override
    public void update(Entity<LinkedList<Entity<GameBoardGameObject>>> entity, double time) {
        if (Snake.isGoingToMove(entity, time)) {
            Snake.move(entity);
        } else {
            Snake.incrementDelayed(entity, time);
        }
    }
}
