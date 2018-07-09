package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.States.State;

public class Bird {

    private final State state;
    private Vector2 position;
    private Vector2 velocity;
    private Texture bird;
    private static final int GRAVITY = -15;
    private float moveSpeed;
    private Animation anim;
    private Vector3 touchPos;
    Rectangle bounds;
    private boolean faceRight, needFlip;


    public Rectangle getBounds() {
        return bounds;
    }

    public Bird(int x, int y, State s) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        bird = new Texture("run_cycle.png");
        moveSpeed = 150;
        anim = new Animation(new TextureRegion(bird), 8, 1f, 3, 3);
        state = s;
        touchPos = new Vector3();
        bounds = new Rectangle(position.x, position.y, getTexture().getRegionWidth(), getTexture().getRegionHeight());

    }

    public void update(float dt) {


        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        }

        if (touchPos.x != 0) {

            // move right
            if (touchPos.x > position.x) {
                if (faceRight) needFlip = true;
                faceRight = false;
                velocity.set(moveSpeed, velocity.y);
            }


            //move left
            if (touchPos.x < position.x) {
                if (!faceRight) needFlip = true;
                faceRight = true;
                velocity.set(-moveSpeed, velocity.y);
            }
            if (needFlip) {
                needFlip = false;
                anim.flipFrames();
            }
            anim.update(dt);

            if (Math.abs(position.x - touchPos.x) < 1) {
                {
                    touchPos.x = 0;
                }

            }


        } else {
            velocity.x = 0;
            anim.setFrame(0);
        }

        velocity.scl(dt);
        position.add(velocity);
        velocity.scl(1 / dt);

        bounds.setPosition(position.x, position.y);

        System.out.println(velocity.x);
    }

    public TextureRegion getTexture() {
        return anim.getFrame();
    }

    public Vector2 getPosition() {
        return position;
    }

}
