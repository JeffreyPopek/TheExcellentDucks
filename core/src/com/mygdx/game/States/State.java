package com.mygdx.game.States;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected State(GameStateManager stateManager) {
        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        mouse = new Vector3();
        gsm = stateManager;
    }

    public OrthographicCamera cam;

    protected Vector3 mouse;

    protected GameStateManager gsm;


    protected abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();


}