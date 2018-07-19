package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Sprites.Controller;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.TheExcellentDucks;

public class PlayState extends State {

    public Player player;
    Controller controller;
    Texture bg;
    public World world;
    Box2DDebugRenderer debugRenderer;




    public PlayState(GameStateManager stateManager) {
        super(stateManager);

        Box2D.init();

        world = new World(new Vector2(0, -9.8f), true);

        player = new Player(this, 50,100);

        bg = new Texture("backgound.png");

        controller = new Controller(cam);

        debugRenderer = new Box2DDebugRenderer();






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
        world.step(1/60f, 6, 2);

    }




    @Override
    protected void handleInput() {

    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0, TheExcellentDucks.WIDTH * State.PTM, TheExcellentDucks.HEIGHT * State.PTM);
        sb.draw(player.getTexture(), player.getBounds().x * State.PTM, player.getPosition().y * State.PTM, player.getTexture().getRegionWidth() * State.PTM, player.getTexture().getRegionHeight() * State.PTM);
        controller.draw(sb);
        sb.end();
        debugRenderer.render(world, cam.combined);
    }

    @Override
    public void dispose() {

    }
}
