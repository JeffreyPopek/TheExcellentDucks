package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.States.PlayState;
import com.mygdx.game.States.State;

public class Player {

    private final State state;
    private Vector2 position;
    private Vector2 velocity;
    private Texture bird;
    private static final int GRAVITY = -15;
    private float moveSpeed;
    private Animation anim;
    private Vector3 touchPos;
    Rectangle bounds;
    private boolean faceRight;
    private BodyDef playerDef;
    public Body playerBody;
    private PolygonShape polygon;
    private FixtureDef fixtureDef;
    PlayState game;
    public final float MAX_VELOCITY = 1.0f;







    public Rectangle getBounds() {
        return bounds;
    }

    public Player(PlayState game, int x, int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        bird = new Texture("Character/WALKING_CHARACTER_1 2.png");
        moveSpeed = 150;
        anim = new Animation(new TextureRegion(bird), 8, 1f, 3, 3);
        state = game;
        touchPos = new Vector3();
        bounds = new Rectangle(position.x, position.y, getTexture().getRegionWidth() * State.PTM, getTexture().getRegionHeight() * State.PTM);
        playerDef = new BodyDef();
        fixtureDef = new FixtureDef();
        polygon = new PolygonShape();
        playerDef.type = BodyDef.BodyType.DynamicBody;
        playerDef.position.set(x * State.PTM, y * State.PTM);
        this.game = game;
        playerBody = game.world.createBody(playerDef);
        polygon.set(new float[] {0, 0, getTexture().getRegionWidth() * State.PTM, 0,
                getTexture().getRegionWidth() * State.PTM, getTexture().getRegionHeight() * State.PTM, 0, getTexture().getRegionHeight() * State.PTM});
        fixtureDef.shape = polygon;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        playerBody.createFixture(fixtureDef);
        polygon.dispose();
        playerBody.setFixedRotation(false);








    }

    public void jump() {
        velocity.y = 250;

    }

    public void moveLeft() {
        if (!faceRight) { anim.flipFrames(); }
        faceRight = true;
        if(playerBody.getLinearVelocity().x < MAX_VELOCITY) {
            playerBody.applyLinearImpulse(new Vector2(-5, 0), playerBody.getPosition(), true);

        }

    }

    public void moveRight() {
        if (faceRight) { anim.flipFrames(); }
        faceRight = false;

        if(playerBody.getLinearVelocity().x < MAX_VELOCITY) {
            playerBody.applyLinearImpulse(new Vector2(5, 0), playerBody.getPosition(), true);

        }

    }

    public void resetAnim() {
        velocity.x = 0;

    }


    public void update(float dt) {

        if (velocity.x != 0) {
            anim.update(dt);
        }




//        velocity.scl(dt);
//        position.add(velocity);
//        velocity.scl(1 / dt);
//
//        if (position.y < 0) {
//            position.y = 0;
//        } else {
//            velocity.add(0, GRAVITY);
//
//        }


        bounds.setPosition(position.x, position.y);
        position.set(playerBody.getPosition());

    }

    public TextureRegion getTexture() {
        return anim.getFrame();
    }

    public Vector2 getPosition() {
        return position;
    }

}
