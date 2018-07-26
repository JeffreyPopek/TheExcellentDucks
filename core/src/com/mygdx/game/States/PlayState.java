package com.mygdx.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
import com.mygdx.game.Sprites.Door;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.TheExcellentDucks;

import javax.swing.plaf.TableHeaderUI;

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
    ShapeRenderer shapeRenderer;
    Door door;
    OrthographicCamera cam2;




    public PlayState(GameStateManager stateManager) {
        super(stateManager);
//        cam2 = new OrthographicCamera();
//        cam2.setToOrtho(false, TheExcellentDucks.WIDTH * State.PTM / 1.5f, TheExcellentDucks.HEIGHT * State.PTM / 1.5f);
        shapeRenderer = new ShapeRenderer();
        map = new TmxMapLoader().load("Levels/level.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, State.PTM);
        cam.setToOrtho(false, TheExcellentDucks.WIDTH * State.PTM, TheExcellentDucks.HEIGHT * State.PTM);

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

        door = new Door(120, 3);




    }


    @Override
    public void update(float dt) {
        cam.position.set(player.playerBody.getPosition(), 0);
        cam.update();
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
            player.jump();
        }
        world.step(1/60f, 6, 2);
        if (door.isCollided(player.getBounds())) {
            gsm.set(new RoomState(gsm, player, controller));
        }
    }




    @Override
    protected void handleInput() {

    }

    @Override
    public void render(SpriteBatch sb) {
        shapeRenderer.setProjectionMatrix(cam.combined);
        sb.setProjectionMatrix(cam.combined);

        renderer.setView(cam);
        renderer.render();
        sb.begin();
        sb.draw(bg, 0, 0, TheExcellentDucks.WIDTH * State.PTM, TheExcellentDucks.HEIGHT * State.PTM);
        sb.draw(door.getTexture(), door.getPosition().x, door.getPosition().y, door.getTexture().getWidth() * State.PTM, door.getTexture().getHeight() * State.PTM);
        sb.draw(player.getTexture(), player.playerBody.getPosition().x, player.playerBody.getPosition().y, player.getTexture().getRegionWidth() * State.PTM, player.getTexture().getRegionHeight() * State.PTM);
        sb.end();



        sb.begin();
        controller.draw(sb);
        sb.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(player.getBounds().x, player.getBounds().y, player.getBounds().width, player.getBounds().height);
        shapeRenderer.rect(door.getBounds().x, door.getBounds().y, door.getBounds().width, door.getBounds().height);
        shapeRenderer.end();

        //controller.drawDebug(shapeRenderer);
        debugRenderer.render(world, cam.combined);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void hide() {

    }
}
