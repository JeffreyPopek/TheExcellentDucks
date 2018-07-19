package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.css.Rect;

public class Door {
    private Texture door;
    private Vector2 position;
    private Rectangle bounds;
    public float height;






    public Door(float x, float y) {
        position = new Vector2(x, y);
        door = new Texture("Textures/door.png");
        bounds = new Rectangle(position.x, position.y, getTexture().getWidth(), getTexture().getHeight());
        height = 64.0f;


    }

    public boolean isCollided(Rectangle otherBounds) {
        if (bounds.overlaps(otherBounds)) {
            return true;
        }
        return false;
    }

    public Texture getTexture() {
        return door;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }
}







