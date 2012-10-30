package com.signalsin.jetpacking.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.signalsin.jetpacking.Config;
import com.signalsin.jetpacking.resources.ImageCache;

public class Platform {
	
	private Vector3 position;
	private Rectangle bounds;
	private TextureRegion frame;
	
	public Platform(){
		
		frame = ImageCache.getTexture("grassPlatform");
		position = new Vector3(0, 0, 0);
		bounds = new Rectangle(0, 0, frame.getRegionWidth(), frame.getRegionHeight());
		
	}
	
	public Platform(float x, float y){
		
		frame = ImageCache.getTexture("grassPlatform");
		position = new Vector3(x, y, 0);
		bounds = new Rectangle(x, y, frame.getRegionWidth(), frame.getRegionHeight());
		
	}
	
	//get methods
	public Rectangle getBounds(){
		return bounds;
	}
	
	//set methods
	
	
	//other methods
	public void draw(SpriteBatch spriteBatch){
		
		spriteBatch.draw(frame, position.x * Config.CHANGE_X,
				position.y * Config.CHANGE_Y,
				frame.getRegionWidth() * Config.CHANGE_X,
				frame.getRegionHeight() * Config.CHANGE_Y);
		
	}
	
	
}
