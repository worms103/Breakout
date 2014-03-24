package com.breakout.render;

import com.breakout.game.Gameloop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class ScreenView extends SurfaceView implements SurfaceHolder.Callback {
	Gameloop gameLoop;
	Paint paint;
	
    public ScreenView(Context context){
        super(context);
        getHolder().addCallback(this);
        gameLoop = new Gameloop(getHolder(), this);
        paint = new Paint();
    }

    public void render(Canvas canvas) {  
        //Fill canvas with white
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        
        canvas.drawRect(10f, 10f, 20f, 20f, paint);
    }

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		gameLoop.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.d("Breakout", "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				gameLoop.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		Log.d("Breakout", "Thread was shut down cleanly");
	}
}