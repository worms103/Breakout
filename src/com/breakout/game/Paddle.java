package com.breakout.game;

import android.graphics.Point;

import com.breakout.CANVAS.render.Screen;

public class Paddle extends Block {
	
	public void init() {
		position.x = Screen.width / 2;
    	position.y = Screen.height - (Screen.height / 20);
    	width = Screen.width / 5;
    	height = Screen.height / 100;
	}
	
	@Override
	public Ball.HitDetection detectHit(Point position, int size) {
		Ball.HitDetection detectedHit = super.detectHit(position, size);
		if(detectedHit.equals(Ball.HitDetection.HIT_TOP)) {
			detectedHit = position.x - (width / 2) < getLeft() ? Ball.HitDetection.PADDLE_LEFT : Ball.HitDetection.PADDLE_RIGHT;
		}
		return detectedHit;
	}
}
