package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.TheExcellentDucks;

public class RoomState extends State{

    Texture bg;

    public RoomState(GameStateManager stateManager) {
        super(stateManager);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, TheExcellentDucks.WIDTH, TheExcellentDucks.HEIGHT);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
