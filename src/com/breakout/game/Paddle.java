package com.breakout.game;

import com.breakout.CANVAS.render.Screen;

public class Paddle extends Block {
	
	public void init() {
		position.x = Screen.width / 2;
    	position.y = Screen.height - (Screen.height / 20);
    	width = Screen.width / 2;
    	height = Screen.height / 20;
	}
}
