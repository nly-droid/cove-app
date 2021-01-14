package com.github.nlydroid.coveapp;

import Screen.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class CoveApplication extends Game {
	public SpriteBatch batch;
	public static final int WORLD_WIDTH = 960;
  public static final int WORLD_HEIGHT = 640;
  public Array<String> questions;
  public Array<String> answers;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
		questions = new Array<>();
		answers = new Array<>();
		questions.add("This internship is different from others. Explain what drew your interest and why it appeals to you ");
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
