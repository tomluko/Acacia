package com.tomluk.acacia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tomluk.acacia.entities.EntityFactory;
import com.tomluk.acacia.entities.components.snake.ControlComponent;
import com.tomluk.acacia.entities.components.snake.ScoreComponent;
import com.tomluk.entities.Entities;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readSettings();
        setContentView(R.layout.activity_game);
        GameView gameView = new GameView(this, new GameRendererImpl(), new GameUpdaterImpl()) {
            @Override
            protected void onSizeChanged(int w, int h, int oldw, int oldh) {
                super.onSizeChanged(w, h, oldw, oldh);
                startGame(w, h);
            }
        };
        FrameLayout frameLayout = findViewById(R.id.frame_layout);
        frameLayout.addView(gameView);
        setupUI();
    }

    private void readSettings() {
        Settings.FOOD_PAINT = Settings.createFillPaint(getResources().getColor(R.color.color_food, null));
        Settings.SNAKE_PAINT = Settings.createFillPaint(getResources().getColor(R.color.color_snake, null));
        Settings.GAME_BOARD_WIDTH_IN_CELLS = Integer.parseInt(getResources().getString(R.string.game_board_width_in_cells));
        Settings.MOVE_DELAY_SNAKE = Double.parseDouble(getResources().getString(R.string.move_delay_snake));
        Settings.DIFFICULTY_STEP = Double.parseDouble(getResources().getString(R.string.difficulty_step));
    }

    private void setupUI() {
        View up = findViewById(R.id.button_up);
        View down = findViewById(R.id.button_down);
        View left = findViewById(R.id.button_left);
        View right = findViewById(R.id.button_right);
        up.setOnClickListener(e -> ControlComponent.up());
        down.setOnClickListener(e -> ControlComponent.down());
        left.setOnClickListener(e -> ControlComponent.left());
        right.setOnClickListener(e -> ControlComponent.right());

        TextView score = findViewById(R.id.score);
        ScoreComponent.setScoreConsumer(text -> score.post(() -> score.setText(text)));
    }

    private void startGame(int w, int h) {
        GameBoard.Instance.dispose();
        Entities.Instance.dispose();
        GameBoard.Instance.init(Settings.GAME_BOARD_WIDTH_IN_CELLS, w, h);
        EntityFactory.createInitialSpawner();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopGame();
    }

    private void stopGame() {
        GameBoard.Instance.dispose();
        Entities.Instance.dispose();
        ScoreComponent.setScoreConsumer(null);
        ControlComponent.reset();
    }
}
