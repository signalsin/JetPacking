package com.signalsin.jetpacking.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {

	Platform platforms[];
	
	int platformCount;
	
	public WorldRenderer(int level){
		
		//read the file corresponding to the level
		//use the data in the file to get value for the game levels
		
		platformCount = 31;
		
		int xCoOrds[] = {0, 24, 48, 72, 96, 120, 144, 168, 192, 216, 240, 264, 288, 312, 336, 360,
							200, 224, 248, 0, 24, 50, 64, 78, 78, 250, 264, 288, 312, 336, 360,
		};
		
		int yCoOrds[] = {
					60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60,
					200, 200, 200, 300, 300, 450, 450, 450, 474, 600, 600, 600, 600, 600, 600
		};
		
		platforms = new Platform[platformCount];
		
		
		for (int i=0; i<platformCount; i++){
			platforms[i] = new Platform(xCoOrds[i], yCoOrds[i]);
		}
		
	}
	
	public Platform getPlatform(int index){
		return platforms[index];
	}
	
	public int getTotalPlatforms(){
		return platforms.length;
	}
	
	public void draw(SpriteBatch spriteBatch){
		
		for (int i=0; i<platforms.length; i++){
			platforms[i].draw(spriteBatch);
		}
	}
	
}

