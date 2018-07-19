package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.TheExcellentDucks;


public class MenuState extends State {


    private Texture background;
    private Texture playBtn;


    public MenuState(GameStateManager stateManager) {
        super(stateManager);
        playBtn = new Texture("playBtn.png");

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }

    }

    @Override
    public void update(float dt) {

        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        //sb.draw(background, 0, 0, TheExcellentDucks.WIDTH, TheExcellentDucks.HEIGHT);
        sb.draw(playBtn, (TheExcellentDucks.WIDTH / 2), TheExcellentDucks.HEIGHT / 2);
        sb.end();

    }

    @Override
    public void dispose() {

        playBtn.dispose();

    }


}
