package com.tomluk.acacia;

import com.tomluk.math.Vector2f;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameBoardTest {

    @Test
    public void move() {
        GameBoard gameBoard = GameBoard.Instance;
        gameBoard.init(1, 1, 5, 6);
        Vector2f moved = gameBoard.move(Vector2f.ZERO, Vector2f.X);
        assertEquals(1f, moved.getX(), 0.0001f);
        assertEquals(0f, moved.getY(), 0.0001f);

        moved = gameBoard.move(Vector2f.ZERO, Vector2f.Y);
        assertEquals(0f, moved.getX(), 0.0001f);
        assertEquals(1f, moved.getY(), 0.0001f);

        moved = gameBoard.move(Vector2f.ZERO, Vector2f.ofX(6));
        assertEquals(0f, moved.getX(), 0.0001f);
        assertEquals(0f, moved.getY(), 0.0001f);

        moved = gameBoard.move(Vector2f.ZERO, Vector2f.ofY(7));
        assertEquals(0f, moved.getX(), 0.0001f);
        assertEquals(0f, moved.getY(), 0.0001f);
    }

    @Test
    public void size() {
        GameBoard gameBoard = GameBoard.Instance;
        gameBoard.init(1, 1, 5, 6);
        Vector2f size = gameBoard.getSize();
        assertEquals(5f, size.getX(), 0.0001f);
        assertEquals(6f, size.getY(), 0.0001f);

        gameBoard.init(3, 2, 5, 6);
        size = gameBoard.getSize();
        assertEquals(1f, size.getX(), 0.0001f);
        assertEquals(3f, size.getY(), 0.0001f);
    }

    @Test
    public void insets() {
        GameBoard gameBoard = GameBoard.Instance; gameBoard.init(3, 4, 5, 6);
        Vector2f insets = gameBoard.getInsets();
        assertEquals(1f, insets.getX(), 0.0001f);
        assertEquals(1f, insets.getY(), 0.0001f);
    }
}