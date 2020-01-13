package com.tomluk.acacia.entities.components.snake;

import com.tomluk.acacia.entities.Snake;
import com.tomluk.acacia.entities.gameobjects.GameBoardGameObject;
import com.tomluk.entities.ComponentAdapter;
import com.tomluk.entities.Entity;
import com.tomluk.math.Vector2f;

import java.util.LinkedList;

public class ControlComponent extends ComponentAdapter<LinkedList<Entity<GameBoardGameObject>>> {

    private static Vector2f direction = Vector2f.Y;

    @Override
    public void update(Entity<LinkedList<Entity<GameBoardGameObject>>> entity, double time) {
        Snake.setDirection(entity, direction);
    }

    public static void up() {
        direction = Vector2f.Y.negate();
    }

    public static void down() {
        direction = Vector2f.Y;
    }

    public static void left() {
        direction = Vector2f.X.negate();
    }

    public static void right() {
        direction = Vector2f.X;
    }

    public static void reset() {
        direction = Vector2f.Y;
    }
}
