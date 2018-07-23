package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Sprites.Controller;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.TheExcellentDucks;

public class PlayState extends State {

    public Player player;
    Controller controller;
    Texture bg;
    public World world;
    Box2DDebugRenderer debugRenderer;
    TiledMap map;
    TiledMapRenderer renderer;
    Body groundBody;
    BodyDef groundDef;
    PolygonShape groundShape;





    public PlayState(GameStateManager stateManager) {
        super(stateManager);

        map = new TmxMapLoader().load("Levels/levels.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, State.PTM);
        Box2D.init();

        world = new World(new Vector2(0, -9.8f), true);

        player = new Player(this, 50,100);

        bg = new Texture("Textures/background.png");

        controller = new Controller(cam);

        debugRenderer = new Box2DDebugRenderer();

        groundDef = new BodyDef();
        groundShape = new PolygonShape();

        Array<Body> grounds = new Array<Body>();
        int counter = 0;
        for (PolygonMapObject obj : map.getLayers().get("collision").getObjects().getByType(PolygonMapObject.class)) {
            groundDef.position.set(obj.getPolygon().getX() * State.PTM, obj.getPolygon().getY() * State.PTM);
            grounds.add(world.createBody(groundDef));
            float[] vertices = obj.getPolygon().getVertices();
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = vertices[i] * State.PTM;
            }
            groundShape.set(vertices);
            grounds.get(counter).createFixture(groundShape, 0.0f);
            counter++;
        }






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
        renderer.setView(cam);
        renderer.render();

        sb.begin();
        //sb.draw(bg, 0, 0, TheExcellentDucks.WIDTH * State.PTM, TheExcellentDucks.HEIGHT * State.PTM);
        sb.draw(player.getTexture(), player.getBounds().x, player.getPosition().y, player.getTexture().getRegionWidth() * State.PTM, player.getTexture().getRegionHeight() * State.PTM);
        controller.draw(sb);
        sb.end();
        debugRenderer.render(world, cam.combined);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void hide() {

    }
}
