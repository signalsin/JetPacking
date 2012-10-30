package com.signalsin.jetpacking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.signalsin.jetpacking.resources.ImageCache;

public class Controls {
	
	public static TextureRegion leftArrow;
	public static TextureRegion rightArrow;
	public static TextureRegion button;
	
	public static String state = "";
	
	public static Vector3 leftArrowPosition;
	public static Vector3 rightArrowPosition;
	public static Vector3 buttonPosition;
	
	public static int leftArrowWidth;
	public static int leftArrowHeight;
	public static int rightArrowWidth;
	public static int rightArrowHeight;
	public static int buttonWidth;
	public static int buttonHeight;
	
	@SuppressWarnings("unused")
	private ControlsInputProcessor inputProcessor;
	
	//constructor
	public Controls (){
		
		leftArrow = ImageCache.getTexture("leftArrow");
		rightArrow = ImageCache.getTexture("rightArrow");
		button = ImageCache.getTexture("button");
		
		leftArrowPosition = new Vector3(10, 10, 0);
		rightArrowPosition = new Vector3(100, 10, 0);
		buttonPosition = new Vector3(Config.VIRTUAL_WIDTH - 65, 10, 0);
		state = "";
		
		leftArrowWidth = leftArrow.getRegionWidth();
		leftArrowHeight = leftArrow.getRegionHeight();
		rightArrowWidth = rightArrow.getRegionWidth();
		rightArrowHeight = rightArrow.getRegionHeight();
		buttonWidth = button.getRegionWidth();
		buttonHeight = button.getRegionHeight();
		
		ControlsInputProcessor inputProcessor = new ControlsInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
	}
	
	public void draw(SpriteBatch spriteBatch, OrthographicCamera camera){
		
		spriteBatch.draw(leftArrow, leftArrowPosition.x, 
				(leftArrowPosition.y),
				leftArrowWidth,
				leftArrowHeight * Config.CHANGE_Y);
			
		spriteBatch.draw(rightArrow, rightArrowPosition.x * Config.CHANGE_X, 
				rightArrowPosition.y * Config.CHANGE_Y,
				rightArrowWidth * Config.CHANGE_X,
				rightArrowHeight * Config.CHANGE_Y);
			
		spriteBatch.draw(button, buttonPosition.x * Config.CHANGE_X, 
				buttonPosition.y * Config.CHANGE_Y,
				buttonWidth * Config.CHANGE_X,
				buttonHeight * Config.CHANGE_Y);
		
	}
	
}
