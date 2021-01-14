package com.github.nlydroid.coveapp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Robot extends Actor {
  private Sprite robot;
  private int direction;
  private static final int SPEED = 700;
  public static final int LEFT = 0;
  public static final int RIGHT = 1;

  public Robot(float x, float y) {
    setPosition(x, y);
    robot = new Sprite(new Texture(Gdx.files.internal("robot_yellowDrive1.png")));
    setScale((float) 2/5);

    setWidth(robot.getWidth());
    setHeight(robot.getHeight());

    setOrigin(0,  0);
    direction = RIGHT;
  }

  public Sprite getSprite() {
    return robot;
  }

  public void dispose(){
    robot.getTexture().dispose();
  }

  public void moveRight(float dt){
    setX(getX() + SPEED * dt);
  }

  public void moveLeft(float dt){
    setX(getX() - SPEED * dt);
  }

  @Override
  public void draw(Batch batch, float parentAlpha){
    robot.setPosition(getX(), getY());
    robot.setScale(getScaleX(), getScaleY());
    robot.setOrigin(getOriginX(), getOriginY());

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
}
