package com.breakout.game;

import com.breakout.CANVAS.render.Screen;

import android.graphics.Point;

public class Ball {
	public Point position = new Point(0, 0);
	
	private int size;
	
	private int xMovement = 10;
	private int yMovement = 10;
	
	private boolean dead;
	
	Paddle paddle;
	
	public void init() {
		size = Screen.width / 20;
	}
	
	public void onLoop(Paddle paddle) {
		if(paddle.detectHit(position, size)) {
			position.y += yMovement = -10;
		}
		else {
			position.x += calculateXMovement();
			position.y += calculateYMovement();
		}
	}
	
	private int calculateXMovement() {
		if(position.x < 0) {
			return xMovement = 10;
		}
		else if(position.x > Screen.width) {
			return xMovement = -10;
		}
		return xMovement;
	}
	
	private int calculateYMovement() {
		if(position.y < 0) {
			return yMovement = 10;
		}
		else if(position.y > Screen.height) {
			dead = true;
			return yMovement = 0;
		}
		return yMovement;
	}
	
	// Properties
	
	public int getSize() {
		return size;
	}
	
	public boolean isDead() {
		return dead;
	}
}
