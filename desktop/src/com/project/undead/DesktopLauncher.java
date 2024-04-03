package com.project.undead;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] arg) {

		ScreenSize screen = new ScreenSize();

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Undead Zamurai");
		config.setWindowedMode(screen.SCREENWIDTH, screen.SCREENHEIGHT);
		config.setResizable(false);
		new Lwjgl3Application(new GameClass(), config);
	}
}
