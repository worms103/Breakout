package com.breakout.game;

import android.graphics.Point;

public class Block {
	
	public Point position = new Point();
	protected int height = 20;
	protected int width = 20;
	
	
	public boolean detectHit(Point position, int size) {
		if(position.x > getLeft() && position.x < getRight() && position.y > getTop()) {
			return true;
		}
		return false;
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
		return position.x + (width / 2);
	}
	
	public int getBottom() {
		return position.y + (width / 2);
	}
	
	public int getXMiddle() {
		return getRight() - ((getRight() - getLeft()) / 2);
	}
	
	public int getYMiddle() {
		return getBottom() - ((getBottom() - getTop()) / 2);
	}
	
}
