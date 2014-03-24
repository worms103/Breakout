package com.breakout.activity;

import android.app.Activity;
import android.os.Bundle;

import com.breakout.render.ScreenView;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupGame();
	}
	
	private void setupGame(){
        setContentView(new ScreenView(this));
	}

}
