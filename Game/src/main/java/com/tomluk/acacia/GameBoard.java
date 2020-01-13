package com.tomluk.acacia;

import com.tomluk.acacia.entities.gameobjects.GameBoardGameObject;
import com.tomluk.entities.Entity;
import com.tomluk.math.Vector2f;
import com.tomluk.math.Vector2i;

import java.util.*;

public enum GameBoard {

    Instance;

    private Vector2f size = Vector2f.ZERO;
    private Vector2f insets = Vector2f.ZERO;
    private Map<Vector2i, Entity<GameBoardGameObject>> entities = new HashMap<>();

    private Random random = new Random();

    public void init(int widthInNumberOfCells, int widthInPixels, int heightInPixels) {
        int cellSizeInPixels = widthInPixels / widthInNumberOfCells;
        init(cellSizeInPixels, cellSizeInPixels, widthInPixels, heightInPixels);
    }

    public void init(int cellWidthInPixels, int cellHeightInPixels, int widthInPixels, int heightInPixels) {
        Settings.GAME_BOARD_CELL_WIDTH_IN_PIXELS = cellWidthInPixels;
        Settings.GAME_BOARD_CELL_HEIGHT_IN_PIXELS = cellHeightInPixels;
        int width = widthInPixels / cellWidthInPixels - 1;
        int height = heightInPixels / cellHeightInPixels - 1;
        size = Vector2f.of(width, height);
        int widthInset = (widthInPixels - width * cellWidthInPixels - cellWidthInPixels) / 2;
        int heightInset = (heightInPixels - height * cellHeightInPixels - cellHeightInPixels) / 2;
        insets = Vector2f.of(widthInset, heightInset);
    }

    public Vector2f move(Vector2f position, Vector2f moveVector) {
        return applyCorrection(position.add(moveVector));
    }

    private Vector2f applyCorrection(Vector2f position) {
        float x;
        float y;

        if (position.getX() > size.getX()) {
            x = 0;
        } else if (position.getX() < 0) {
            x = size.getX();
        }
        else {
            x = position.getX();
        }
        if (position.getY() > size.getY()) {
            y = 0;
        } else if (position.getY() < 0) {
            y = size.getY();
        }
        else {
            y = position.getY();
        }
        return Vector2f.of(x, y);
    }

    public Vector2f getMiddlePosition() {
        return size.scale(0.5f).toVector2i().toVector2f();
    }

    public Vector2f getRandomEmptyPosition() {
        if (hasEmptySpace()) {
            Vector2f randomPosition;
            Vector2i s = size.toVector2i();
            do {
                randomPosition = Vector2f.of(random.nextInt(s.getX()), random.nextInt(s.getY()));
            }
            while (getEntity(randomPosition) != null);
            return randomPosition;
        }
        return Vector2f.ZERO;
    }

    private boolean hasEmptySpace() {
        return size.getX() * size.getY() > getAllEntities().size();
    }

    public Vector2f getSize() {
        return size;
    }

    public Vector2f getInsets() {
        return insets;
    }

    public void addEntity(Entity<GameBoardGameObject> entity, Vector2f position) {
        entities.put(position.toVector2i(), entity);
    }

    public void removeEntity(Vector2f position) {
        entities.remove(position.toVector2i());
    }

    public Entity<GameBoardGameObject> getEntity(Vector2f position) {
        return entities.get(position.toVector2i());
    }

    public Collection<Entity<GameBoardGameObject>> getAllEntities() {
        return new ArrayList<>(entities.values());
    }

    public void dispose() {
        size = Vector2f.ZERO;
        insets = Vector2f.ZERO;
        entities.clear();
    }
}
