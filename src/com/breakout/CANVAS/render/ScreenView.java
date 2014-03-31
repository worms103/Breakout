package com.breakout.CANVAS.render;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.breakout.activity.GameActivity;
import com.breakout.game.Ball;
import com.breakout.game.Gameloop;
import com.breakout.game.Paddle;
import com.breakout.game.ScoreBlock;
import com.breakout.game.ScoreBlock.Levels;

public class ScreenView extends SurfaceView implements SurfaceHolder.Callback {
	Paint paint;
	Gameloop gameLoop;
	GameActivity gameActivity;
	Paddle paddle;
	Ball ball;
	
	int width;
	int height;
	boolean gameOver;
	boolean gameInit = false;
	private int score;
	
    public ScreenView(Context context){
        super(context);
        gameActivity = (GameActivity) context;
        getHolder().addCallback(this);
        gameLoop = new Gameloop(getHolder(), this, gameActivity);
        paint = new Paint();
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
    	paddle = gameActivity.getPaddle();
        ball = gameActivity.getBall();
        
    	Screen.width = width;
    	Screen.height = height - (height / 5);
    	
    	paddle.init();
    	ball.init();
    	gameActivity.setLevel(Levels.LEVEL_ONE);
    	
    	gameLoop.unpause();
    	gameInit = true;
    }

    public void render(Canvas canvas) {
    	if(!gameInit) {
    		return;
    	}
    	
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
        	drawBall(canvas);
        	drawScoreBlocks(canvas);
        }
    }
    
    private void drawPaddle(Canvas canvas) {
    	paint.setColor(Color.BLACK);
    	canvas.drawRect(paddle.getLeft(), paddle.getTop(), 
    					paddle.getRight(), paddle.getBottom(), paint);
    }
    
    private void drawBall(Canvas canvas) {
    	paint.setColor(Color.BLACK);
    	canvas.drawCircle(ball.position.x, ball.position.y, ball.getSize(), paint);
    }
    
    private void drawScoreBlocks(Canvas canvas) {
    	int borderSize = 8;
    	for(ScoreBlock scoreBlock : gameActivity.getScoreBlocks()) {
    		paint.setColor(Color.RED);
    		canvas.drawRect(scoreBlock.getLeft(), scoreBlock.getTop(), 
    				scoreBlock.getRight(), scoreBlock.getBottom(), paint);
    		paint.setColor(Color.BLACK);
    		canvas.drawRect(scoreBlock.getLeft() + borderSize, scoreBlock.getTop() + borderSize, 
    				scoreBlock.getRight() - borderSize, scoreBlock.getBottom() - borderSize, paint);
    	}	
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
		gameLoop.pause();
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent e) {
    	int posX = ((int) e.getX()) - 5;
    	if(posX != paddle.position.x) {
    		paddle.position.x = posX - (paddle.getWidth() / 2);
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