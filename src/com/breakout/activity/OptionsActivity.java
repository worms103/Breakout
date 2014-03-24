package com.breakout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.breakout.R;

public class OptionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
	}
	
	public void goBackClicked(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
