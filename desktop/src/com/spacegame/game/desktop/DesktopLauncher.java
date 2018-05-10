package com.spacegame.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spacegame.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "Space Game";
                config.width = 960;
                config.height = 540;
                config.resizable = false;
		new LwjglApplication(new Game(), config);
	}
}
