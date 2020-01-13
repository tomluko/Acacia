package com.tomluk.acacia.entities.components.snake;

import com.tomluk.acacia.Settings;
import com.tomluk.acacia.entities.Snake;
import com.tomluk.acacia.entities.gameobjects.GameBoardGameObject;
import com.tomluk.entities.ComponentAdapter;
import com.tomluk.entities.Entity;

import java.util.LinkedList;

public class DifficultyComponent extends ComponentAdapter<LinkedList<Entity<GameBoardGameObject>>> {

    private int length;
    private int initialLength;

    @Override
    public void onAdd(Entity<LinkedList<Entity<GameBoardGameObject>>> entity) {
        length = Snake.getLength(entity);
        initialLength = length;
    }

    @Override
    public void update(Entity<LinkedList<Entity<GameBoardGameObject>>> entity, double time) {
        int newLength = Snake.getLength(entity);
        if (length < newLength) {
            GameBoardGameObject head = Snake.getHead(entity);
            head.setMoveDelay(Settings.MOVE_DELAY_SNAKE - Settings.DIFFICULTY_STEP * (length - initialLength));
            length = newLength;
        }
    }
}
