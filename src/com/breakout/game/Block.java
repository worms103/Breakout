package com.breakout.game;

import android.graphics.Point;

public class Block {
	
	public Point position = new Point();
	protected int height = 0;
	protected int width = 0;
	
	public Ball.HitDetection detectHit(Point position, int size) {
		if(position.x > getLeft() && position.x < getRight() && position.y > getTop() && position.y < getBottom()) {
			if(position.x - Ball.xMovementSpeed <= getLeft()) {
				return Ball.HitDetection.HIT_LEFT;
			}
			else if(position.y - Ball.yMovementSpeed <= getTop()) {
				return Ball.HitDetection.HIT_TOP;
			}
			else if(position.x + Ball.xMovementSpeed >= getRight()) {
				return Ball.HitDetection.HIT_RIGHT;
			}
			else if(position.y + Ball.yMovementSpeed >= getBottom()) {
				return Ball.HitDetection.HIT_BOTTOM;
			}
		}
		return Ball.HitDetection.NONE;
	}
	
	// Properties
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

	public int getLeft() {
		return position.x;
	}
	
	public int getTop() {
		return position.y;
	}
	
	public int getRight() {
		return position.x + width;
	}
	
	public int getBottom() {
		return position.y + height;
	}
	
	public int getXMiddle() {
		return getRight() - ((getRight() - getLeft()) / 2);
	}
	
	public int getYMiddle() {
		return getBottom() - ((getBottom() - getTop()) / 2);
	}
	
}
