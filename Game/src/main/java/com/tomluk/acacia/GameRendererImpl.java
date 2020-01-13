package com.tomluk.acacia;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.tomluk.acacia.entities.gameobjects.GameBoardGameObject;
import com.tomluk.entities.Entity;
import com.tomluk.math.Vector2f;

class GameRendererImpl implements GameRenderer {

    @Override
    public void render(Canvas canvas, double time) {
        for (Entity<GameBoardGameObject> entity : GameBoard.Instance.getAllEntities()) {
            render(entity, canvas);
        }
    }

    private void render(Entity<GameBoardGameObject> entity, Canvas canvas) {
        GameBoardGameObject gameObject = entity.getGameObject();
        Paint paint = gameObject.getPaint();
        Vector2f size = gameObject.getSize();
        Vector2f position = gameObject.getPosition();
        Vector2f insets = GameBoard.Instance.getInsets();
        float left = size.getX() * position.getX() + insets.getX();
        float top = size.getY() * position.getY() + insets.getY();
        float right = left + size.getX();
        float bottom = top + size.getY();
        canvas.drawRect(left, top, right, bottom, paint);
    }
}
