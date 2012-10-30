package com.signalsin.jetpacking.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.signalsin.jetpacking.Config;
import com.signalsin.jetpacking.resources.ImageCache;


public class Player {
	
	private TextureRegion frame;
	private float stateTime;
	private float speed;
	private Vector3 position;
	private String facing;
	private float velocity;
	private Rectangle bounds;
	private boolean hasKey;
	private boolean isFalling;
	private boolean onPlatform;
	
	//empty constructor
	public Player(){
		
	    frame = ImageCache.getTexture("jetpackrobot");
	    stateTime = 0f;
	    speed = 125.0f;
	    position = new Vector3(0, 0, 0);
	    facing = "right";
	    bounds = new Rectangle(0, 0, frame.getRegionWidth(), frame.getRegionHeight());
	    hasKey = false;
	    isFalling = false;
	    onPlatform = false;
	}
	
	//constructor passing the co-ordinates
	public Player(float x, float y){
		
		frame = ImageCache.getTexture("jetpackrobot");
	    stateTime = 0f;
	    speed = 125.0f;
	    position = new Vector3(x, y, 0);
	    facing = "right";
	    bounds = new Rectangle(x, y, (frame.getRegionWidth() - 5) * Config.CHANGE_X, (frame.getRegionHeight() - 5) * Config.CHANGE_Y);
	    hasKey = false;
	    isFalling = false;
	    onPlatform = false;
	    
	}
	
	//get methods
	public TextureRegion getFrame(){
		return frame;
	}
	
	public float getStateTime(){
		return stateTime;
	}
	
	public float getSpeed(){
		return speed;
	}
	
	public Vector3 getPosition(){
		return position;
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}
	
	public String getFacing(){
		return facing;
	}
	
	public float getVelocity(){
		return velocity;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public boolean getHasKey(){
		return hasKey;
	}
	
	public boolean getIsFalling(){
		return isFalling;
	}
	
	public boolean onPlatform(){
		return onPlatform;
	}
	
	//set methods
	public void setFrame(TextureRegion value){
		frame = value;
	}
	
	public void setStateTime(float value){
		stateTime = value;
	}
	
	public void setSpeed(float value){
		speed = value;
	}
	
	public void setPosition(Vector3 value){
		position = value;
	}
	
	public void setX(float value){
		position.x = value;
	}
	
	public void setY(float value){
		position.y = value;
	}
	
	public void setVelocity(float value){
		velocity = value;
	}
	
	public void setBounds(Rectangle value){
		bounds = value;
	}
	
	public void setHasKey(boolean value){
		hasKey = value;
	}
	
	public void setIsFalling(boolean value){
		isFalling = value;
	}
	
	public void setOnPlatform(boolean value){
		onPlatform = value;
	}
	
	//update all player details ie co-ordinates, animations etc
	public void update(){
		
		if ((position.y < 0.01) && (isFalling)){
			onPlatform = true;
		}
		
		if (onPlatform){
			velocity = 0;
			isFalling = false;
		}
		
		if (velocity > 0){
			isFalling = false;
		}
		else
		{
			isFalling = true;
			velocity = -(Gdx.graphics.getDeltaTime() * (speed * 2));
		}
		
		applyVelocity();
		
		//move the bounds to match the sprite
		bounds.setX(position.x);
		bounds.setY(position.y);
		
	}
	
	//render the player
	public void draw(SpriteBatch spriteBatch){
		if (facing == "right"){
			spriteBatch.draw(frame, position.x * Config.CHANGE_X, 
					position.y * Config.CHANGE_Y,
					frame.getRegionWidth() * Config.CHANGE_X,
					frame.getRegionHeight() * Config.CHANGE_Y);
		}
		else if (facing == "left"){
			spriteBatch.draw(frame,
					(position.x * Config.CHANGE_X) + (frame.getRegionWidth() * Config.CHANGE_X),
					(position.y * Config.CHANGE_Y),
					-(frame.getRegionWidth() * Config.CHANGE_X),
					frame.getRegionHeight() * Config.CHANGE_Y);
		}
	}
	
	//movement methods
	public void moveLeft(){
		//check to make sure sprite is on screen
		//if (position.x > 0){
			position.x -= Gdx.graphics.getDeltaTime() * speed;
		//}
		
		if (facing == "right") {
			facing = "left";
		}
	}
	
	public void moveRight(){
		//if (position.x < (Config.VIRTUAL_WIDTH - frame.getRegionWidth())){
			position.x += Gdx.graphics.getDeltaTime() * speed;
		//}
		
		if (facing == "left"){
			facing = "right";
		}
	}
	
	private void applyVelocity(){
		position.y += velocity;
	}
	
}
