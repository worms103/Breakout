package com.breakout.game;

import android.graphics.Point;

public class Paddle {
	Point pos;
	
	public Paddle() {
		pos = new Point();
		pos.x = 0;
		pos.y = 0;
	}
	
	public Point getPos() {
		return pos;
	}
}
