package com.github.nlydroid.coveapp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Robot extends Actor {
  private Sprite robot;
  private int direction;
  private static final int SPEED = 850;
  public static final int LEFT = 0;
  public static final int RIGHT = 1;
  private Rectangle bound;
  private Music spinning;

  public Robot(float x, float y) {
    setPosition(x, y);
    robot = new Sprite(new Texture(Gdx.files.internal("robot_yellowDrive1.png")));
    setScale((float) 2/5);

    setWidth(robot.getWidth());
    setHeight(robot.getHeight());

    setOrigin(0,  0);
    direction = RIGHT;

    bound = robot.getBoundingRectangle();

    spinning = Gdx.audio.newMusic(Gdx.files.internal("Sound/spinning.ogg"));
    spinning.setVolume(0.2f);

  }

  public Sprite getSprite() {
    return robot;
  }

  public void dispose(){
    robot.getTexture().dispose();
    spinning.dispose();
  }

  public void moveRight(float dt){
    setX(getX() + SPEED * dt);
    if (!spinning.isPlaying()){
      spinning.play();
    }
  }

  public void moveLeft(float dt){
    setX(getX() - SPEED * dt);
    if (!spinning.isPlaying()){
      spinning.play();
    }
  }

  @Override
  public void draw(Batch batch, float parentAlpha){
    robot.setPosition(getX(), getY());
    robot.setScale(getScaleX(), getScaleY());
    robot.setOrigin(getOriginX(), getOriginY());
    bound = robot.getBoundingRectangle();

    if (direction == RIGHT){
      robot.setFlip(false, false);
    }
    else {
      robot.setFlip(true, false);
    }
    robot.draw(batch);
  }

  public int getDirection() {
    return direction;
  }

  public void switchDirection(int direction){
    if (direction == RIGHT || direction == LEFT){
      this.direction = direction;
    }
  }

  public Rectangle getBound() {
    return bound;
  }
}
