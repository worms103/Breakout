package com.breakout.game;

import java.util.Date;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.breakout.render.ScreenView;

public class Gameloop extends Thread {
	
	private SurfaceHolder surface;
	private ScreenView screenView;

	public Gameloop(SurfaceHolder surfaceHolder, ScreenView screenView) {
		this.surface = surfaceHolder;
		this.screenView = screenView;
	}
	
	@Override
	public void run() {
		Canvas canvas = null;
		long loopTimer = new Date().getTime();
		boolean gameOver = false;
//		while(!gameOver){
//			// Do stuff
//			render(canvas);
//		}
		render(canvas);
	}

	private void render(Canvas canvas) {
		// Going to need to add some logic for rendering openGL
	}
}
