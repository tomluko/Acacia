package com.tomluk.acacia.entities;

import com.tomluk.acacia.GameBoard;
import com.tomluk.acacia.Settings;
import com.tomluk.acacia.entities.components.food.SpawnFoodComponent;
import com.tomluk.acacia.entities.components.snake.ControlComponent;
import com.tomluk.acacia.entities.components.snake.DeathComponent;
import com.tomluk.acacia.entities.components.snake.DifficultyComponent;
import com.tomluk.acacia.entities.components.snake.EatComponent;
import com.tomluk.acacia.entities.components.snake.MoveComponent;
import com.tomluk.acacia.entities.components.snake.ScoreComponent;
import com.tomluk.acacia.entities.components.spawner.SpawnComponent;
import com.tomluk.acacia.entities.gameobjects.GameBoardGameObject;
import com.tomluk.entities.Entities;
import com.tomluk.entities.Entity;
import com.tomluk.math.Vector2f;

import java.util.LinkedList;

public class EntityFactory {

    public static void createFood(Vector2f position) {
        GameBoardGameObject gameObject = new GameBoardGameObject();
        gameObject.setPosition(position);
        gameObject.setSize(getGameBoardCellSize());
        gameObject.setPaint(Settings.FOOD_PAINT);
        gameObject.setFood(true);
        gameObject.setDeadly(false);

        Entity<GameBoardGameObject> entity = new Entity<>(gameObject);
        entity.addComponent(new SpawnFoodComponent());

        GameBoard.Instance.addEntity(entity, position);
        Entities.Instance.addEntity(entity);
    }

    public static Entity<GameBoardGameObject> createSnakeSegment(Vector2f position) {
        GameBoardGameObject gameObject = new GameBoardGameObject();
        gameObject.setPosition(position);
        gameObject.setSize(getGameBoardCellSize());
        gameObject.setPaint(Settings.SNAKE_PAINT);
        gameObject.setFood(false);
        gameObject.setDeadly(true);

        Entity<GameBoardGameObject> entity = new Entity<>(gameObject);

        GameBoard.Instance.addEntity(entity, position);
        Entities.Instance.addEntity(entity);
        return entity;
    }

    public static void createSnake(Vector2f position) {
        LinkedList<Entity<GameBoardGameObject>> gameObject = new LinkedList<>();
        Entity<GameBoardGameObject> head = createSnakeSegment(position);
        GameBoardGameObject headGameObject = head.getGameObject();
        headGameObject.setDirection(Vector2f.Y);
        headGameObject.setMoveDelay(Settings.MOVE_DELAY_SNAKE);
        gameObject.addFirst(head);

        Entity<LinkedList<Entity<GameBoardGameObject>>> entity = new Entity<>(gameObject);
        entity.addComponent(new ControlComponent());
        entity.addComponent(new DeathComponent());
        entity.addComponent(new EatComponent());
        entity.addComponent(new MoveComponent());
        entity.addComponent(new ScoreComponent());
        entity.addComponent(new DifficultyComponent());

        Entities.Instance.addEntity(entity);
    }

    public static void createInitialSpawner() {
        Object gameObject = new Object();

        Entity<Object> entity = new Entity<>(gameObject);
        entity.addComponent(new SpawnComponent());

        Entities.Instance.addEntity(entity);
    }

    private static Vector2f getGameBoardCellSize() {
        return Vector2f.of(Settings.GAME_BOARD_CELL_WIDTH_IN_PIXELS, Settings.GAME_BOARD_CELL_HEIGHT_IN_PIXELS);
    }
}
