package com.signalsin.jetpacking.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.signalsin.jetpacking.Config;
import com.signalsin.jetpacking.resources.ImageCache;

public class Key {

	private TextureRegion frame;
	private Vector3 position;
	private Rectangle bounds;
	private boolean isVisible;
	
	//constructors
	
	public Key(){
		
		frame = ImageCache.getTexture("key");
		position = new Vector3(20,20,0);
		bounds = new Rectangle(20, 20, frame.getRegionWidth(), frame.getRegionHeight());
		isVisible = true;
	}
	
	public Key(float x, float y){
		
		frame = ImageCache.getTexture("key");
		position = new Vector3(x,y,0);
		bounds = new Rectangle(x, y, frame.getRegionWidth(), frame.getRegionHeight());
		isVisible = true;
	}
	
	//get methods
	public TextureRegion getFrame(){
		return frame;
	}
	
	public Vector3 getPosition(){
		return position;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public boolean getVisibility(){
		return isVisible;
	}
	
	//set methods
	public void setVisibility(boolean value){
		isVisible = value;
	}
	
	//other methods
	public void draw(SpriteBatch spriteBatch){
		if (isVisible){
			spriteBatch.draw(frame, position.x * Config.CHANGE_X, 
				position.y * Config.CHANGE_Y,
				frame.getRegionWidth() * Config.CHANGE_X,
				frame.getRegionHeight() * Config.CHANGE_Y);
		}
	}
	
	
}
