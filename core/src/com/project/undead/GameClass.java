package com.project.undead;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.undead.screens.GameMainMenu;
import com.project.undead.screens.GameMapSelect;
import com.project.undead.screens.GameOver;
import com.project.undead.screens.GameScreen;

public class GameClass extends Game {

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new GameMapSelect(this));
	}

	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
