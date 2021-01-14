package com.github.nlydroid.coveapp;

import Screen.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CoveApplication extends Game {
	public SpriteBatch batch;
	public static final int WORLD_WIDTH = 960;
  public static final int WORLD_HEIGHT = 640;


	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
	}
}
