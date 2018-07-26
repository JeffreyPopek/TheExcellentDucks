package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.States.State;
import com.mygdx.game.TheExcellentDucks;

import java.util.HashMap;
import java.util.Map;

/*
Note to self:
To use InputListener, would need a way to reference the pointer that initiated the button to
correctly implement touchUp.
 */

public class Controller implements InputProcessor {
    private OrthographicCamera camera; // We need this to unproject our tap coordinates
    private Array<Image> buttons;

    // Different depending on what buttons you have
    private Rectangle leftHitbox, rightHitbox;
    private Rectangle jumpHitbox, attackHitbox;

    private boolean leftPressed, rightPressed, jumpPressed, attackPressed;

    class TouchInfo {
        public float touchX = 0;
        public float touchY = 0;
        public boolean touched = false;
    }

    private Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();

    public Controller(OrthographicCamera camera) {
        this.camera = camera;

        buttons = new Array<Image>();

        buttons = new Array<Image>();

        Image left = new Image(new Texture("buttons/Left arrow.png"));
        left.setScale(State.PTM * 2);
        left.setPosition(0, 0);
        leftHitbox = new Rectangle(left.getX(), left.getY(), left.getWidth() * left.getScaleX(), left.getHeight() * left.getScaleY());
        buttons.add(left);

        Image right = new Image(new Texture("buttons/Right arrow.png"));
        right.setScale(State.PTM * 2);
        right.setPosition((left.getWidth() * left.getScaleX() + 4 * State.PTM) , 0);
        rightHitbox = new Rectangle(right.getX(), right.getY(), right.getWidth() * right.getScaleX(), right.getHeight() * right.getScaleY());
        buttons.add(right);

        Image jump = new Image(new Texture("buttons/Up Arrow.png"));
        jump.setScale(State.PTM * 2);
        jump.setPosition(TheExcellentDucks.WIDTH * State.PTM - jump.getWidth() * State.PTM * 2, 0);
        jumpHitbox = new Rectangle(jump.getX(), jump.getY(), jump.getWidth() * jump.getScaleX(), jump.getHeight() * jump.getScaleY());
        buttons.add(jump);

//        Image attack = new Image(new Texture("textures/buttons/attack-button.png"));
//        attack.setPosition(TheExcellentDucks.WIDTH - attack.getWidth(), jump.getHeight() + 4);
//        attackHitbox = new Circle(attack.getX() + attack.getWidth() / 2, attack.getY() + attack.getHeight() / 2, attack.getWidth() / 2);
//        buttons.add(attack);

        Gdx.input.setInputProcessor(this);
        for (int i = 0; i < 5; i++) {
            touches.put(i, new TouchInfo());
        }
    }

    public void update() {
//        for (int i = 0; i < 5; i++) {
//            Vector3 touchPos = new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0);
//            camera.unproject(touchPos);
//
//            if (leftHitbox.contains(touchPos.x, touchPos.y)) {
//                leftPressed = Gdx.input.isTouched(i);
//                rightPressed = false;
//            } else if (rightHitbox.contains(touchPos.x, touchPos.y)) {
//                rightPressed = Gdx.input.isTouched(i);
//                leftPressed = false;
//            } else if (jumpHitbox.contains(touchPos.x, touchPos.y)) {
//                jumpPressed = Gdx.input.isTouched(i);
//            }
//        }
    }

    public void draw(SpriteBatch batch) {
        for (Image i : buttons) {
            i.draw(batch, 0.8f);
        }
    }

    public void drawDebug(ShapeRenderer sr) {
        sr.rect(leftHitbox.x, leftHitbox.y, leftHitbox.width, leftHitbox.height);
        sr.rect(rightHitbox.x * State.PTM, rightHitbox.y * State.PTM, rightHitbox.width * State.PTM, rightHitbox.height * State.PTM);
        sr.rect(jumpHitbox.x, jumpHitbox.y, jumpHitbox.width, jumpHitbox.height);
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isJumpPressed() {
        return jumpPressed;
    }

    public boolean isAttackPressed() {
        return attackPressed;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchpos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchpos);

        if (pointer < 5) {
            touches.get(pointer).touchX = touchpos.x;
            touches.get(pointer).touchY = touchpos.y;
            touches.get(pointer).touched = true;

            if (leftHitbox.contains(touchpos.x, touchpos.y)) {
                leftPressed = true;
                rightPressed = false;
            } else if (rightHitbox.contains(touchpos.x, touchpos.y)) {
                rightPressed = true;
                leftPressed = false;
            } else if (jumpHitbox.contains(touches.get(pointer).touchX, touches.get(pointer).touchY)) {
                jumpPressed = true;
                System.out.println("hi");

            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX + "," + screenY);
        if (pointer < 5) {
            touches.get(pointer).touchX = 0;
            touches.get(pointer).touchY = 0;
            touches.get(pointer).touched = false;

            leftPressed = false;
            rightPressed = false;
            jumpPressed = false;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println(screenX + "," + screenY);
        Vector3 touchPos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPos);
        if (pointer < 5) {
            touches.get(pointer).touchX = touchPos.x;
            touches.get(pointer).touchY = touchPos.y;

            if (leftHitbox.contains(touchPos.x, touchPos.y)) {
                leftPressed = touches.get(pointer).touched;
                rightPressed = false;
            } else if (rightHitbox.contains(touchPos.x, touchPos.y)) {
                rightPressed = touches.get(pointer).touched;
                leftPressed = false;
            } else if (jumpHitbox.contains(touchPos.x, touchPos.y)) {
                jumpPressed = touches.get(pointer).touched;
            } else {
                leftPressed = false;
                rightPressed = false;
                jumpPressed = false;
            }
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}