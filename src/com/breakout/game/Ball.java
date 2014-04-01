package com.breakout.game;

import com.breakout.CANVAS.render.Screen;

import android.graphics.Point;

public class Ball {
	public Point position = new Point(0, 0);
	
	private int size;
	
	public static final int xSpeed = 10;
	public static final int ySpeed = 10;
	
	public static final int xMovementSpeed = 1;
	public static final int yMovementSpeed = 1;
	
	private int xMovement = xMovementSpeed;
	private int yMovement = -yMovementSpeed;
	
	private boolean dead;
	
	public void init() {
		size = Screen.width / 100;
		position.x = Screen.width / 2;
    	position.y = Screen.height / 2;
	}
	
	public void onLoop(Block block) {
		HitDetection detectedHit = block.detectHit(position, size);
		if(!detectedHit.equals(HitDetection.NONE)) {
			switch(detectedHit) {
				case HIT_LEFT:
					xMovement = -xMovementSpeed;
					break;
				case HIT_TOP:
					yMovement = -yMovementSpeed;
					break;
				case HIT_RIGHT:
					xMovement = xMovementSpeed;
					break;
				case HIT_BOTTOM:
					yMovement = yMovementSpeed;
					break;
				case PADDLE_LEFT:
					yMovement = -yMovementSpeed;
					xMovement = -xMovementSpeed;
					break;
				case PADDLE_RIGHT:
					yMovement = -yMovementSpeed;
					xMovement = xMovementSpeed;
				default:
					break;
			}
		}
	}
	
	public void moveBall() {
		position.x += calculateXMovement();
		position.y += calculateYMovement();
	}
	
	private int calculateXMovement() {
		if(position.x < 0) {
			return xMovement = xMovementSpeed;
		}
		else if(position.x > Screen.width) {
			return xMovement = -xMovementSpeed;
		}
		return xMovement;
	}
	
	private int calculateYMovement() {
		if(position.y < 0) {
			return yMovement = yMovementSpeed;
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
	
	// Static Methods
	
	public enum HitDetection {
		NONE,
		HIT_LEFT,
		HIT_TOP,
		HIT_RIGHT,
		HIT_BOTTOM,
		PADDLE_LEFT,
		PADDLE_RIGHT
	}
}
