package com.breakout.game;

import java.util.ArrayList;
import java.util.List;

import com.breakout.CANVAS.render.Screen;

import android.graphics.Point;

public class ScoreBlock extends Block {
	
	private int health = 1;
	private boolean alive = true;
	
	public ScoreBlock(Point position) {
		this.position = position;
		width = Screen.width / maxColumns;
    	height = Screen.height / maxRows;
	}
	
	@Override
	public Ball.HitDetection detectHit(Point position, int size) {
		Ball.HitDetection detectedHit = super.detectHit(position, size);
		if(!detectedHit.equals(Ball.HitDetection.NONE)) {
			if(health == 1) {
				alive = false;
			}
			health--;
		}
		return detectedHit;
	}
	
	public int getHealth() {
		return health;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	// Static Methods
	
	private static int maxColumns = 9;
	private static int maxRows = 30;
	
	public static void init(List<ScoreBlock> scoreBlocks) {
		scoreBlocks = initBlocks(Levels.LEVEL_ONE);
	}
	
	public static ScoreBlockList initBlocks(Levels levelChoice) {
		switch(levelChoice) {
			case LEVEL_ONE: 
				return initLevelOne();
			default:
				System.out.println("Default level choisen, error in code");
				return new ScoreBlockList();
		}
	}
	
	private static ScoreBlockList initLevelOne() {
		List<Point> points = new ArrayList<Point>(9);
		points.add(getScaledPosition(new Point(0, 1)));
		points.add(getScaledPosition(new Point(1, 2)));
		points.add(getScaledPosition(new Point(2, 3)));
		points.add(getScaledPosition(new Point(3, 4)));
		points.add(getScaledPosition(new Point(4, 5)));
		points.add(getScaledPosition(new Point(5, 4)));
		points.add(getScaledPosition(new Point(6, 3)));
		points.add(getScaledPosition(new Point(7, 2)));
		points.add(getScaledPosition(new Point(8, 1)));
		
		return generateScoreBlockList(points);
	}

	private static Point getScaledPosition(Point point) {
		return new Point(point.x * (Screen.width / maxColumns), point.y * (Screen.height / maxRows));
	}
	
	private static ScoreBlockList generateScoreBlockList(List<Point> positions) {
		ScoreBlockList scoreBlocks = new ScoreBlockList();
		for(Point position : positions) {
			scoreBlocks.add(new ScoreBlock(position));
		}
		return scoreBlocks;
	}
	
	public static enum Levels {
		EMPTY,
		LEVEL_ONE
	};
	
}
