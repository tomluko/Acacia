package com.tomluk.acacia.entities.components.snake;

import com.tomluk.acacia.entities.Snake;
import com.tomluk.acacia.entities.gameobjects.GameBoardGameObject;
import com.tomluk.entities.ComponentAdapter;
import com.tomluk.entities.Entity;

import java.util.LinkedList;

public class ScoreComponent extends ComponentAdapter<LinkedList<Entity<GameBoardGameObject>>> {

    private static Consumer<String> scoreConsumer;

    private int score;
    private int length;

    @Override
    public void onAdd(Entity<LinkedList<Entity<GameBoardGameObject>>> entity) {
        length = Snake.getLength(entity);
    }

    @Override
    public void update(Entity<LinkedList<Entity<GameBoardGameObject>>> entity, double time) {
        int newLength = Snake.getLength(entity);
        if (length < newLength) {
            increaseScore();
            length = newLength;
        }
        scoreConsumer.accept(Integer.toString(score));
    }

    private void increaseScore() {
        score += 10;
    }

    public static void setScoreConsumer(Consumer<String> scoreConsumer) {
        ScoreComponent.scoreConsumer = scoreConsumer;
    }

    // for compatibility, delete when better smartphone available :)
    public interface Consumer<T> {
        void accept(T t);
    }
}
