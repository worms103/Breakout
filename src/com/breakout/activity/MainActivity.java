package com.breakout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.breakout.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
	}
	
	public void startGameClicked(View view) {
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}
	
	public void optionsClicked(View view) {
		Intent intent = new Intent(this, OptionsActivity.class);
		startActivity(intent);
	}
	
	public void exitClicked(View view) {
		finish();
	}

}
