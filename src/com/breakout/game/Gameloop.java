package com.breakout.game;

import java.util.Date;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.breakout.CANVAS.render.ScreenView;

public class Gameloop extends Thread {
	
	private SurfaceHolder surfaceHolder;
	private ScreenView screenView;
	private boolean paused;
	
	public Gameloop(SurfaceHolder surfaceHolder, ScreenView screenView2) {
		this.surfaceHolder = surfaceHolder;
		this.screenView = screenView2;
	}

	@Override
	public void run() {
		Canvas canvas = null;
		long loopTimer = new Date().getTime();
		boolean gameOver = false;
		while(!gameOver) {
			while(paused) {
				waitForUnpause();
			}
			long newLoopTimer = new Date().getTime();
			if (newLoopTimer > loopTimer + 200) {
				render(canvas);
			}
		}
		render(canvas);
	}

	private void waitForUnpause() {
		try { 
			sleep(100); 
		} catch (InterruptedException e) { 
			e.printStackTrace(); 
		}
	}

	private void render(Canvas canvas) {
		canvas = null;
		// try locking the canvas for exclusive pixel editing
		// in the surface
		try {
			canvas = surfaceHolder.lockCanvas();
			synchronized (surfaceHolder) {
				screenView.render(canvas);				
			}
		} finally {
			// in case of an exception the surface is not left in 
			// an inconsistent state
			if (canvas != null) {
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}	
	}
	
	public void pause() {
		paused = true;
	}
	
	public void unpause() {
		paused = false;
	}
}
