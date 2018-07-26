package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
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
    public final float MAX_VELOCITY = 2.0f;







    public Rectangle getBounds() {
        return bounds;
    }

    public Player(PlayState game, int x, int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        bird = new Texture("Character/WALKING_CHARACTER_1 2.png");
        moveSpeed = 150;
        anim = new Animation(new TextureRegion(bird), 3, 1f, 2, 2);
        state = game;
        touchPos = new Vector3();

        playerDef = new BodyDef();
        fixtureDef = new FixtureDef();
        polygon = new PolygonShape();
        playerDef.type = BodyDef.BodyType.DynamicBody;
        playerDef.position.set(x * State.PTM, y * State.PTM);
        this.game = game;
        playerBody = game.world.createBody(playerDef);
        polygon.set(new float[] {getTexture().getRegionWidth() * State.PTM / 3, 0, getTexture().getRegionWidth() * State.PTM * 2 / 3, 0,
                getTexture().getRegionWidth() * State.PTM * 2 / 3, getTexture().getRegionHeight() * 2 / 3 * State.PTM, getTexture().getRegionWidth() * State.PTM / 3, getTexture().getRegionHeight() * 2 / 3 * State.PTM});
        fixtureDef.shape = polygon;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 2f;
        playerBody.createFixture(fixtureDef);
        polygon.dispose();
        playerBody.setFixedRotation(true);
        bounds = new Rectangle(getTexture().getRegionWidth() * State.PTM / 3, 0, getTexture().getRegionWidth() * State.PTM / 3, getTexture().getRegionHeight() * 2 / 3 * State.PTM);
    }

    public void jump() {
        if (playerBody.getLinearVelocity().y == 0) {
            playerBody.applyLinearImpulse(new Vector2(0, 0.5f), playerBody.getPosition(), true);
        }

    }

    public void moveLeft() {
        if (!faceRight) { anim.flipFrames(); }
        faceRight = true;
        if(playerBody.getLinearVelocity().x > -MAX_VELOCITY) {
            playerBody.applyLinearImpulse(new Vector2(-0.5f, 0), playerBody.getPosition(), true);

        }

    }

    public void moveRight() {
        if (faceRight) { anim.flipFrames(); }
        faceRight = false;

        if(playerBody.getLinearVelocity().x < MAX_VELOCITY) {
            playerBody.applyLinearImpulse(new Vector2(0.5f, 0), playerBody.getPosition(), true);

        }

    }

    public void resetAnim() {
        velocity.x = 0;

    }


    public void update(float dt) {
        if (playerBody.getLinearVelocity().x != 0) {
            anim.update(dt);
        } else {
            anim.setFrame(0);
        }

        bounds.setPosition(playerBody.getPosition().x + bounds.getWidth(), playerBody.getPosition().y);
        position.set(playerBody.getPosition());

    }

    public TextureRegion getTexture() {
        return anim.getFrame();
    }

    public Vector2 getPosition() {
        return position;
    }

}
