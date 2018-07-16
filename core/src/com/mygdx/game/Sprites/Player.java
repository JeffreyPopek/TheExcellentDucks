package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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


    public Rectangle getBounds() {
        return bounds;
    }

    public Player(int x, int y, State s) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        bird = new Texture("run_cycle.png");
        moveSpeed = 150;
        anim = new Animation(new TextureRegion(bird), 8, 1f, 3, 3);
        state = s;
        touchPos = new Vector3();
        bounds = new Rectangle(position.x, position.y, getTexture().getRegionWidth(), getTexture().getRegionHeight());

    }

    public void jump() {
        velocity.y = 250;

    }

    public void moveLeft() {
        if (!faceRight) { anim.flipFrames(); }
        faceRight = true;
        velocity.x = -250;
    }

    public void moveRight() {
        if (faceRight) { anim.flipFrames(); }
        faceRight = false;

        velocity.x = 250;
    }

    public void resetAnim() {
        velocity.x = 0;

    }


    public void update(float dt) {

        if (velocity.x != 0) {
            anim.update(dt);
        }


        velocity.scl(dt);
        position.add(velocity);
        velocity.scl(1 / dt);

        if (position.y < 0) {
            position.y = 0;
        } else {
            velocity.add(0, GRAVITY);

        }


        bounds.setPosition(position.x, position.y);

    }

    public TextureRegion getTexture() {
        return anim.getFrame();
    }

    public Vector2 getPosition() {
        return position;
    }

}
