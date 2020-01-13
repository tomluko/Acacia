package com.tomluk.acacia;

public interface GameUpdater {

    GameUpdater EMPTY = new GameUpdater() {
        @Override
        public void update(double time) {
            // do nothing
        }
    };

    /**
     * @param time is given between updates, in ms
     */
    void update(double time);
}
