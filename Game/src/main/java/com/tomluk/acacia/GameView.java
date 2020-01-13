package com.tomluk.acacia;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.tomluk.loop.BasicGameLoop;
import com.tomluk.loop.GameScene;

public class GameView extends SurfaceView implements GameScene, SurfaceHolder.Callback {

    private BasicGameLoop gameLoop;
    private GameRenderer gameRenderer = GameRenderer.EMPTY;
    private GameUpdater gameUpdater = GameUpdater.EMPTY;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, GameRenderer gameRenderer, GameUpdater gameUpdater) {
        super(context);
        this.gameRenderer = gameRenderer;
        this.gameUpdater = gameUpdater;
        init();
    }

    private void init() {
        setFocusable(true);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoop = createGameLoop();
        gameLoop.startRunning();
    }

    private BasicGameLoop createGameLoop() {
        return new BasicGameLoop(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // do nothing
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameLoop.stopRunning();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
//    }

    @Override
    public void update(double time) {
        gameUpdater.update(time);
    }

    @Override
    public void draw(double time) {
        Canvas canvas = null;
        SurfaceHolder surfaceHolder = getHolder();
        try {
            canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                synchronized (surfaceHolder) {
                    draw(canvas);
                    gameRenderer.render(canvas, time);
                }
            }
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
