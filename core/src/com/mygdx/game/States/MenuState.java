package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.TheExcellentDucks;




public class MenuState extends State {
    Texture img;
    Image playbutton;


    private Texture background;
    private Texture playBtn;


    public MenuState(GameStateManager stateManager) {
        super(stateManager);
        playBtn = new Texture("buttons/PlayButton.png");
        background =  new Texture("Textures/labescape.png");
        playbutton = new Image(playBtn);
        playbutton.setScale(2);
        playbutton.setPosition(TheExcellentDucks.WIDTH / 2 - playbutton.getWidth() * playbutton.getScaleX() / 2, TheExcellentDucks.HEIGHT / 5 - playbutton.getHeight() * playbutton.getScaleY() / 2);

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }

    }

    @Override
    public void hide() {

    }

    @Override
    public void update(float dt) {

        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(background, 0, 0, TheExcellentDucks.WIDTH, TheExcellentDucks.HEIGHT);
        playbutton.draw(sb, 1);
        sb.end();

    }

    @Override
    public void dispose() {

        background.dispose();
        playBtn.dispose();

    }


}
