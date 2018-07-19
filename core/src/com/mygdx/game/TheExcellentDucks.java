package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.States.GameStateManager;
import com.mygdx.game.States.MenuState;

public class TheExcellentDucks extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final String TITLE = "TheExcellentDucks";
	private GameStateManager gsm;

	@Override
	public void create() {
		batch = new SpriteBatch();
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
		Gdx.gl.glClearColor(0, 0, 0, 1);

	}


	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}


	@Override
	public void dispose() {
		batch.dispose();
	}
}
