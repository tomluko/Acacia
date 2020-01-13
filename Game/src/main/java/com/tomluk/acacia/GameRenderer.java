package com.tomluk.acacia;

import android.graphics.Canvas;

public interface GameRenderer {

    GameRenderer EMPTY = (canvas, time) -> {
        // do nothing
    };

    /**
     * @param time is given between frames drawn, in ms
     */
    void render(Canvas canvas, double time);
}
