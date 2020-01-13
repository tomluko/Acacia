package com.tomluk.acacia.entities.components.snake;

import com.tomluk.acacia.GameBoard;
import com.tomluk.acacia.entities.Food;
import com.tomluk.acacia.entities.Snake;
import com.tomluk.acacia.entities.gameobjects.GameBoardGameObject;
import com.tomluk.entities.ComponentAdapter;
import com.tomluk.entities.Entity;
import com.tomluk.math.Vector2f;

import java.util.LinkedList;

public class EatComponent extends ComponentAdapter<LinkedList<Entity<GameBoardGameObject>>> {

    @Override
    public void update(Entity<LinkedList<Entity<GameBoardGameObject>>> entity, double time) {
        if (!Snake.isGoingToMove(entity, time)) {
            return;
        }
        Vector2f nextPosition = Snake.getNextMovementPosition(entity);
        Entity<GameBoardGameObject> aheadEntity = GameBoard.Instance.getEntity(nextPosition);
        if (aheadEntity == null) {
            return;
        }
        GameBoardGameObject ahead = aheadEntity.getGameObject();
        if (!ahead.isFood()) {
            return;
        }
        Food.consume(aheadEntity);
        Snake.grow(entity);
    }

}
