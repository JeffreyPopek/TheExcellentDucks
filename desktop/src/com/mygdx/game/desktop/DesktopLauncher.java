package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.TheExcellentDucks;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = TheExcellentDucks.WIDTH;
		config.height = TheExcellentDucks.HEIGHT;
		config.title = TheExcellentDucks.TITLE;
		new LwjglApplication(new TheExcellentDucks(), config);
	}
}
