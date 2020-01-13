package com.tomluk.acacia.entities.gameobjects;

import android.graphics.Paint;
import com.tomluk.math.Vector2f;

public class GameBoardGameObject {

    private Vector2f position;
    private Vector2f size;
    private Paint paint;
    private boolean isFood;
    private boolean isDeadly;

    private double moveDelay;
    private double delayed;
    private Vector2f direction;

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getSize() {
        return size;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public boolean isFood() {
        return isFood;
    }

    public void setFood(boolean food) {
        isFood = food;
    }

    public boolean isDeadly() {
        return isDeadly;
    }

    public void setDeadly(boolean deadly) {
        isDeadly = deadly;
    }

    public double getMoveDelay() {
        return moveDelay;
    }

    public void setMoveDelay(double moveDelay) {
        this.moveDelay = moveDelay;
    }

    public double getDelayed() {
        return delayed;
    }

    public void setDelayed(double delayed) {
        this.delayed = delayed;
    }

    public Vector2f getDirection() {
        return direction;
    }

    public void setDirection(Vector2f direction) {
        this.direction = direction;
    }
}
