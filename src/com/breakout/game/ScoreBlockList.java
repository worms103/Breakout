package com.breakout.game;

import java.util.ArrayList;

public class ScoreBlockList extends ArrayList<ScoreBlock> {

	private static final long serialVersionUID = -8070974117715091533L;

	public void onLoop(Ball ball) {
		for(ScoreBlock scoreBlock : this) {
			ball.onLoop(scoreBlock);
		}
	}
}
