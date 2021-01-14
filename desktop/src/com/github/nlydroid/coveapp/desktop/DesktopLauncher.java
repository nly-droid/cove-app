package com.github.nlydroid.coveapp.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.nlydroid.coveapp.CoveApplication;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "My Cove Application";
		config.width = CoveApplication.WORLD_WIDTH;
		config.height = CoveApplication.WORLD_HEIGHT;
		new LwjglApplication(new CoveApplication(), config);
	}
}
