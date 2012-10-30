package com.signalsin.jetpacking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class ControlsInputProcessor implements InputProcessor {
	
	private String[] pointerButtonPressed;
	
	
	public ControlsInputProcessor(){
		//allocate memory for strings
		pointerButtonPressed = new String[2];
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		
		if ((x > Controls.leftArrowPosition.x * Config.CHANGE_X) 
				&& (x < ((Controls.leftArrowPosition.x * Config.CHANGE_X) + (Controls.leftArrowWidth * Config.CHANGE_X))
				&& (y > (Gdx.graphics.getHeight() - ((Controls.leftArrowPosition.y * Config.CHANGE_Y) + (Controls.leftArrowHeight * Config.CHANGE_Y))))
				&& (y < (Gdx.graphics.getHeight() - (Controls.leftArrowPosition.y * Config.CHANGE_Y))))
		) {
			Controls.state = Controls.state + "leftArrow";
			pointerButtonPressed[pointer] = "leftArrow";
		}
		else if ((x > Controls.rightArrowPosition.x * Config.CHANGE_X) 
				&& (x < ((Controls.rightArrowPosition.x * Config.CHANGE_X) + (Controls.rightArrowWidth * Config.CHANGE_X))
				&& (y > (Gdx.graphics.getHeight() - ((Controls.rightArrowPosition.y * Config.CHANGE_Y) + (Controls.rightArrowHeight * Config.CHANGE_Y))))
				&& (y < (Gdx.graphics.getHeight() - (Controls.rightArrowPosition.y * Config.CHANGE_Y))))
		) {
			Controls.state = Controls.state + "rightArrow";
			pointerButtonPressed[pointer] = "rightArrow";
		}
		else if ((x > Controls.buttonPosition.x * Config.CHANGE_X) 
				&& (x < ((Controls.buttonPosition.x * Config.CHANGE_X) + (Controls.buttonWidth * Config.CHANGE_X))
				&& (y > (Gdx.graphics.getHeight() - ((Controls.buttonPosition.y * Config.CHANGE_Y) + (Controls.buttonHeight * Config.CHANGE_Y))))
				&& (y < (Gdx.graphics.getHeight() - (Controls.buttonPosition.y * Config.CHANGE_Y))))
		) {
			//check to make sure that it doesn't add another 'button' before building the string
			if (!Controls.state.contains("button")){
				Controls.state = Controls.state + "button";
				pointerButtonPressed[pointer] = "button";
			}
			
		}
		else {
			pointerButtonPressed[pointer] = "";
			
		}
		
		return true;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		
		Controls.state = (Controls.state.replace(pointerButtonPressed[pointer], ""));
		return false;
	}
	
	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}


}
