package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Sprites.Controller;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.TheExcellentDucks;

public class PlayState extends State {


    Player player;
    Controller controller;
    Texture bg;

    public PlayState(GameStateManager stateManager) {
        super(stateManager);

        player = new Player(50, 100, this);


        bg = new Texture("bg.png");

        controller = new Controller(cam);

    }


    @Override
    public void update(float dt) {
        handleInput();
        player.update(dt);


        if (controller.isLeftPressed()) {
            player.moveLeft();
        } else if (controller.isRightPressed()) {
            player.moveRight();
        } else {
            player.resetAnim();

        }
        if (controller.isJumpPressed()) {
            System.out.println("hi");
            player.jump();
        }
    }




    @Override
    protected void handleInput() {

    }


    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(bg, 0, 0, TheExcellentDucks.WIDTH, TheExcellentDucks.HEIGHT);
        sb.draw(player.getTexture(), player.getBounds().x, player.getPosition().y);
        controller.draw(sb);

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
