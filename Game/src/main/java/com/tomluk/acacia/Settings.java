package com.tomluk.acacia;

import android.graphics.Paint;

public class Settings {

    public static Paint SNAKE_PAINT;
    public static Paint FOOD_PAINT;
    public static int GAME_BOARD_CELL_WIDTH_IN_PIXELS;
    public static int GAME_BOARD_CELL_HEIGHT_IN_PIXELS;
    public static int GAME_BOARD_WIDTH_IN_CELLS;
    public static double MOVE_DELAY_SNAKE;
    public static double DIFFICULTY_STEP;

    public static Paint createFillPaint(int color) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        return paint;
    }
}
