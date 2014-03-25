package com.breakout.CANVAS.render;

import com.breakout.activity.GameActivity;
import com.breakout.game.Gameloop;
import com.breakout.game.Paddle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class ScreenView extends SurfaceView implements SurfaceHolder.Callback {
	Paint paint;
	Gameloop gameLoop;
	GameActivity gameActivity;
	Paddle paddle;
	
	int width;
	int height;
	boolean gameOver;
	boolean drawingStarted;
	private int score;
	
    public ScreenView(Context context){
        super(context);
        gameActivity = (GameActivity) context;
        paddle = gameActivity.getPaddle();
        getHolder().addCallback(this);
        gameLoop = new Gameloop(getHolder(), this);
        paint = new Paint();
    }
    
    public boolean drawingStart(){
    	return drawingStarted;
    }
    
    public void gameOver(){
    	gameOver = true;
    }
    
    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
    	if(this.width == 0) {
    		initGame(width, height);
    	}
        super.onSizeChanged(width, height, oldWidth, oldHeight);
    }
    
    private void initGame(int width, int height) {
    	this.width = width;
    	this.height = height;
    	paddle.position.x = width / 2;
    	paddle.position.y = height - height / 20;
    	paddle.width = width / 2;
    	paddle.height = height / 20;
    }

    public void render(Canvas canvas) {  
        //Fill canvas with white
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        
        if(gameOver) {
        	paint.setColor(Color.BLACK);
        	paint.setTextSize(32f);
        	canvas.drawText("Game Over!", width / 2, height / 2, paint);
        }
        else {
        	drawPaddle(canvas);
        }
        
        drawingStarted = true;
    }
    
    private void drawPaddle(Canvas canvas) {
    	paint.setColor(Color.BLACK);
    	canvas.drawRect(paddle.position.x, paddle.position.y, 
    					paddle.position.x + paddle.width, paddle.position.y + paddle.height, paint);
    }
    
    private void drawScore(Canvas canvas) {
    	paint.setColor(Color.BLACK);
    	paint.setTextSize(16f);
    	canvas.drawText("Score: " + score, 40, 40, paint);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		if(gameLoop.isAlive()) {
			gameLoop.unpause();
		}
		else {
			gameLoop.start();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.d("Snake", "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		gameLoop.pause();
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent e) {
    	int posX = ((int) e.getX()) - 5;
    	if(posX != paddle.position.x) {
    		paddle.position.x = posX - (paddle.width / 2);
    	}

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
        }
        return true;
    }
	
	public void onPause() {
		gameLoop.pause();
	}
	
	public void onResume() {
		gameLoop.unpause();
	}
}