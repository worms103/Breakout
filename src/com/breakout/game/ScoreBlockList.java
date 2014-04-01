package com.breakout.game;

import java.util.ArrayList;

public class ScoreBlockList extends ArrayList<ScoreBlock> {

	private static final long serialVersionUID = -8070974117715091533L;

	public void onLoop(Ball ball) {
		for(int i = 0; i < this.size(); i++) {
			ball.onLoop(this.get(i));
			if(!this.get(i).isAlive()) {
				this.remove(i);
			}
		}
	}
}
