package com.tomluk.acacia;

import static com.tomluk.entities.Entities.Instance;

class GameUpdaterImpl implements GameUpdater {

    @Override
    public void update(double time) {
        Instance.update(time);
    }
}
