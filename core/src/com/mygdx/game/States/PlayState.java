package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Sprites.Controller;
import com.mygdx.game.Sprites.Door;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.TheExcellentDucks;

public class PlayState extends State {


    Player player;
    Controller controller;
    Texture bg;
    Door door;
    ShapeRenderer shapeRenderer = new ShapeRenderer();


    public PlayState(GameStateManager stateManager) {
        super(stateManager);

        player = new Player(50, 100, this);
        door = new Door(400, 0);


        bg = new Texture("Textures/background.png");

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
            if (player.getVelocity().y < 0)
                player.jump();
        }
        // if door touches player bounds, do something
        if (door.isCollided(player.getBounds())) {
           player.setPosition(new Vector2(50, 90));
        }
    }




    @Override
    protected void handleInput() {

    }


    @Override
    public void render(SpriteBatch sb) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(door.getBounds().x, door.getBounds().y, door.getBounds().getWidth(), door.getBounds().getHeight());
        shapeRenderer.rect(player.getBounds().x, player.getBounds().y, player.getBounds().getWidth(), player.getBounds().getHeight());
        shapeRenderer.end();

        sb.begin();
        sb.draw(bg, 0, 0, TheExcellentDucks.WIDTH, TheExcellentDucks.HEIGHT);
        sb.draw(door.getTexture(), door.getPosition().x, door.getPosition().y, door.getTexture().getWidth(), door.height);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);


        controller.draw(sb);

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
