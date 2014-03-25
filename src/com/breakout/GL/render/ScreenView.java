package com.breakout.GL.render;

import android.content.Context;
import android.graphics.Paint;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.breakout.activity.GameActivity;
import com.breakout.game.Gameloop;

public class ScreenView extends GLSurfaceView {
	Gameloop gameLoop;
	GLRender glRender;
	GameActivity gameActivity;
	Paint paint;
	
    public ScreenView(Context context){
        super(context);
        gameActivity = (GameActivity) context;
        
        setEGLContextClientVersion(2);
        glRender = new GLRender(gameActivity);
        setRenderer(glRender);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

    	int posX = ((int) e.getX()) - 5;
    	if(posX != gameActivity.getPaddle().position.x) {
    		gameActivity.getPaddle().position.x = posX;
//    		requestRender();
    	}

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

//                float dx = x - mPreviousX;
//                float dy = y - mPreviousY;
//
//                // reverse direction of rotation above the mid-line
//                if (y > getHeight() / 2) {
//                    dx = dx * -1 ;
//                }
//
//                // reverse direction of rotation to left of the mid-line
//                if (x < getWidth() / 2) {
//                    dy = dy * -1 ;
//                }
//
//                // = 180.0f / 320
//                setAngle(getAngle() + ((dx + dy) * TOUCH_SCALE_FACTOR)); 
//                requestRender();
        }
        return true;
    }

	public void updateGLPaddlePos() {
		
	}
    
}