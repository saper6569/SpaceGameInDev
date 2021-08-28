package com.playper3.space.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.playper3.space.Main;
import com.playper3.space.screens.SetupVars;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = SetupVars.NAME + " v" + SetupVars.VERSION;
		//config.fullscreen = true;
		config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
		config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		config.resizable = false;
		new LwjglApplication(new Main(), config);
	}
}
