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
		canvas = null;
		// try locking the canvas for exclusive pixel editing
		// in the surface
		try {
			canvas = surface.lockCanvas();
			synchronized (surface) {
				screenView.render(canvas);				
			}
		} finally {
			// in case of an exception the surface is not left in 
			// an inconsistent state
			if (canvas != null) {
				surface.unlockCanvasAndPost(canvas);
			}
		}
	}
}
