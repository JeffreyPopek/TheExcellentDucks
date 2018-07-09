package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Sprites.Bird;
import com.mygdx.game.TheExcellentDucks;

public class PlayState extends State {
    Bird bird;
    Texture bg;

    public PlayState(GameStateManager stateManager) {
        super(stateManager);

        bird = new Bird(50, 100, this);


        bg = new Texture("bg.png");


    }


    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);

    }


    @Override
    protected void handleInput() {

    }


    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(bg, 0, 0, TheExcellentDucks.WIDTH, TheExcellentDucks.HEIGHT);
        sb.draw(bird.getTexture(), bird.getBounds().x, bird.getPosition().y);

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
