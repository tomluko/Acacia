package com.tomluk.acacia.entities;

import com.tomluk.acacia.GameBoard;
import com.tomluk.acacia.entities.gameobjects.GameBoardGameObject;
import com.tomluk.entities.Entities;
import com.tomluk.entities.Entity;
import com.tomluk.math.Vector2f;

import java.util.LinkedList;

public class Snake {

    private static final Vector2f DEFAULT_NEW_SNAKE_SEGMENT_POSITION = Vector2f.ONE.negate();

    public static void grow(Entity<LinkedList<Entity<GameBoardGameObject>>> entity) {
        LinkedList<Entity<GameBoardGameObject>> segments = entity.getGameObject();
        Entity<GameBoardGameObject> newTailEntity = EntityFactory.createSnakeSegment(DEFAULT_NEW_SNAKE_SEGMENT_POSITION);
        segments.addLast(newTailEntity);
    }

    public static boolean isGoingToMove(Entity<LinkedList<Entity<GameBoardGameObject>>> entity, double time) {
        GameBoardGameObject head = getHead(entity);
        return head.getDelayed() + time > head.getMoveDelay();
    }

    public static void incrementDelayed(Entity<LinkedList<Entity<GameBoardGameObject>>> entity, double time) {
        GameBoardGameObject head = getHead(entity);
        head.setDelayed(head.getDelayed() + time);
    }

    public static void move(Entity<LinkedList<Entity<GameBoardGameObject>>> entity) {
        GameBoardGameObject head = getHead(entity);
        Vector2f position = getNextMovementPosition(entity);
        Entity<GameBoardGameObject> tailEntity = entity.getGameObject().removeLast();
        GameBoardGameObject tail = tailEntity.getGameObject();
        GameBoard.Instance.removeEntity(tail.getPosition());
        tail.setPosition(position);
        tail.setDelayed(0);
        tail.setDirection(head.getDirection());
        if (head != tail) {
            tail.setMoveDelay(head.getMoveDelay());
            head.setMoveDelay(0);
            head.setDelayed(0);
        }
        entity.getGameObject().addFirst(tailEntity);
        GameBoard.Instance.addEntity(tailEntity, position);
    }

    public static void setDirection(Entity<LinkedList<Entity<GameBoardGameObject>>> entity, Vector2f direction) {
        GameBoardGameObject head = getHead(entity);
        head.setDirection(direction);
    }

    public static Vector2f getNextMovementPosition(Entity<LinkedList<Entity<GameBoardGameObject>>> entity) {
        GameBoardGameObject head = getHead(entity);
        return GameBoard.Instance.move(head.getPosition(), head.getDirection());
    }

    public static void die(Entity<LinkedList<Entity<GameBoardGameObject>>> entity) {
        Entities.Instance.removeEntity(entity);
    }

    public static int getLength(Entity<LinkedList<Entity<GameBoardGameObject>>> entity) {
        return entity.getGameObject().size();
    }

    public static GameBoardGameObject getHead(Entity<LinkedList<Entity<GameBoardGameObject>>> entity) {
        return getHeadEntity(entity).getGameObject();
    }

    private static Entity<GameBoardGameObject> getHeadEntity(Entity<LinkedList<Entity<GameBoardGameObject>>> entity) {
        return entity.getGameObject().getFirst();
    }
}
