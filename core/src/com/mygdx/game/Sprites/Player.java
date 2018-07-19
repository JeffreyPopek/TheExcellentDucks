package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.States.State;
import com.mygdx.game.TheExcellentDucks;

public class Player {

    private final State state;
    private Vector2 position;
    private Vector2 velocity;
    private Texture player;
    private Texture jump;
    private static final int GRAVITY = -15;
    private float moveSpeed;
    private Animation anim;
    private Animation jumpanim;
    private Vector3 touchPos;
    Rectangle bounds;
    private boolean faceRight;
    private int numjump;
    private boolean isJumping = false;



    public Rectangle getBounds() {
        return bounds;
    }

    public Player(int x, int y, State s) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        player = new Texture("WALKING_CHARACTER_1 2.png");
        jump = new Texture("CHARACTER_JUMPING.png");
        moveSpeed = 150;
        anim = new Animation(new TextureRegion(player), 6, 1f, 3, 2);
        jumpanim = new Animation(new TextureRegion(jump),4, 0.7f, 2, 2);
        state = s;
        touchPos = new Vector3();
        bounds = new Rectangle(getTexture().getRegionWidth()/3, position.y, getTexture().getRegionWidth() / 3, getTexture().getRegionHeight() * 2 / 3);
        numjump = 1;

    }

    public void jump() {
        if (numjump > 0) {

            velocity.y = 250;
            numjump--;
        }


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

    public void jumpReset() {
        jumpanim.setFrame(0);
    }


    public void update(float dt) {


        if (velocity.x != 0) {
            anim.update(dt);
        }


        velocity.scl(dt);
        position.add(velocity);
        velocity.scl(1 / dt);

        if (velocity.x == 0)
            anim.setFrame(0);

        if (position.y < 0) {
            position.y = 0;
            numjump = 1;
            isJumping =false;
        } else {
            velocity.add(0, GRAVITY);


        }

        if (position.y > 0) {
            isJumping = true;
            jumpanim.update(dt);
        }
        if (position.x < 0)
            position.x = 0;

        if (position.x > TheExcellentDucks.WIDTH - anim.getFrame().getRegionWidth())
            position.x = TheExcellentDucks.WIDTH - anim.getFrame().getRegionWidth();



        bounds.setPosition(position.x + getTexture().getRegionWidth() / 3, position.y);

    }

    public TextureRegion getTexture() {
        if (isJumping) {
            return jumpanim.getFrame();
        }
        jumpReset();
        return anim.getFrame();
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
