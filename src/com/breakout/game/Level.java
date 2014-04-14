package com.breakout.game;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

import com.breakout.CANVAS.render.Screen;

public class Level {

	public static final int maxColumns = 9;
	public static final int maxRows = 30;
	private Levels currentLevel = Levels.EMPTY;
	
	public void getNextLevel() {
		switch(currentLevel) {
			case EMPTY: 
				currentLevel = Levels.LEVEL_ONE;
				return;
			case LEVEL_ONE: 
				currentLevel = Levels.FINAL_LEVEL;
				return;
			default:
				return;
		}
	}
	
	public ScoreBlockList getScoreBlocks() {
		switch(currentLevel) {
			case LEVEL_ONE: 
				return initLevelOne();
			case FINAL_LEVEL:
				return initLevelTwo();
			default:
				System.out.println("Default level chosen, error in code");
				return new ScoreBlockList();
		}
	}
	
	private ScoreBlockList initLevelOne() {
		List<Point> points = new ArrayList<Point>(9);
		points.add(getScaledPosition(new Point(8, 1)));
		
		return generateScoreBlockList(points);
	}
	
	private ScoreBlockList initLevelTwo() {
		List<Point> points = new ArrayList<Point>(9);
		points.add(getScaledPosition(new Point(5, 0)));
		points.add(getScaledPosition(new Point(5, 1)));
		points.add(getScaledPosition(new Point(5, 2)));
		points.add(getScaledPosition(new Point(5, 3)));
		points.add(getScaledPosition(new Point(5, 4)));
		points.add(getScaledPosition(new Point(5, 5)));
		points.add(getScaledPosition(new Point(5, 6)));
		points.add(getScaledPosition(new Point(5, 7)));
		points.add(getScaledPosition(new Point(5, 8)));
		
		return generateScoreBlockList(points);
	}

	private Point getScaledPosition(Point point) {
		return new Point(point.x * (Screen.width / maxColumns), point.y * (Screen.height / maxRows));
	}
	
	private ScoreBlockList generateScoreBlockList(List<Point> positions) {
		ScoreBlockList scoreBlocks = new ScoreBlockList();
		for(Point position : positions) {
			scoreBlocks.add(new ScoreBlock(position));
		}
		return scoreBlocks;
	}
	
	public enum Levels {
		EMPTY,
		LEVEL_ONE,
		FINAL_LEVEL
	};
	
}
