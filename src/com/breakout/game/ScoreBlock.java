package com.breakout.game;

import android.graphics.Point;

import com.breakout.CANVAS.render.Screen;

public class ScoreBlock extends Block {
	
	private int health = 1;
	private boolean alive = true;
	
	public ScoreBlock(Point position) {
		this.position = position;
		width = Screen.width / Level.maxColumns;
    	height = Screen.height / Level.maxRows;
	}
	
	@Override
	public Ball.HitDetection detectHit(Point position, int size) {
		Ball.HitDetection detectedHit = super.detectHit(position, size);
		if(!detectedHit.equals(Ball.HitDetection.NONE)) {
			if(health == 1) {
				alive = false;
			}
			health--;
			Score.score++;
		}
		return detectedHit;
	}
	
	public int getHealth() {
		return health;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
}
