package com.TeamOrange.NewSquareGame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.TeamOrange.NewSquareGame.NewSquareGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "New Square Game";
        config.width = 960;
        config.height = 540;
		new LwjglApplication(new NewSquareGame(), config);
	}
}
