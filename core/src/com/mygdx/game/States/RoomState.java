package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Sprites.Controller;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.TheExcellentDucks;

public class RoomState extends State{

    Texture bg;
    Player player;
    Controller controller;

    public RoomState(GameStateManager stateManager, Player player, Controller controller) {
        super(stateManager);
        this.player = player;
        this.controller = controller;
        bg = new Texture("Textures/background2.png");
    }

    @Override
    protected void handleInput() {

        if (controller.isLeftPressed()) {
            player.moveLeft();
        } else if (controller.isRightPressed()) {
            player.moveRight();
        } else {
            player.resetAnim();

        }
        if (controller.isJumpPressed()) {
            if (player.getVelocity().y < 0)
                player.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        player.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, TheExcellentDucks.WIDTH, TheExcellentDucks.HEIGHT);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);

        controller.draw(sb);
        sb.end();

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
