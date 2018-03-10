package com.hostileterritory.screens.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hostileterritory.game.HostileTerritory;
import com.hostileterritory.Box2D.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		 config.width = Constants.APP_WIDTH;
		 config.height = Constants.APP_HEIGHT;
		new LwjglApplication(new HostileTerritory(), config);
	}
}
