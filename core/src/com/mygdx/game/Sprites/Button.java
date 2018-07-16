package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public abstract class Button {
    Sprite image;

    public void draw(SpriteBatch batch) {
        image.draw(batch);
    }

    public abstract boolean checkPressed(Vector3 tap);

    public abstract void drawDebug(ShapeRenderer sr);
}
