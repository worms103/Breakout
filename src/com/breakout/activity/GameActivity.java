package com.breakout.activity;

import android.app.Activity;
import android.os.Bundle;

import com.breakout.CANVAS.render.ScreenView;
import com.breakout.game.Ball;
import com.breakout.game.Paddle;
import com.breakout.game.ScoreBlock;
import com.breakout.game.ScoreBlock.Levels;
import com.breakout.game.ScoreBlockList;

public class GameActivity extends Activity {

	ScreenView screenView;
	
	Paddle paddle;
	Ball ball;
	ScoreBlockList blocks;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupGame();
	}
	
	private void setupGame() {
		paddle = new Paddle();
		ball = new Ball();
		blocks = ScoreBlock.initBlocks(Levels.EMPTY);
		
		screenView = new ScreenView(this);
        setContentView(screenView);
	}
	
	public Paddle getPaddle() {
		return paddle;
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public void setLevel(Levels level) {
		blocks = ScoreBlock.initBlocks(level);
	}
	
	public ScoreBlockList getScoreBlocks() {
		return blocks;
	}

    @Override
    protected void onPause() {
        super.onPause();
        screenView.onPause();
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
//        screenView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        screenView.onResume();
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
//        screenView.onResume();
    }

}
