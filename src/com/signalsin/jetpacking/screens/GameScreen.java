package com.signalsin.jetpacking.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import com.signalsin.jetpacking.Config;
import com.signalsin.jetpacking.Controls;
import com.signalsin.jetpacking.JetPackingGame;
import com.signalsin.jetpacking.elements.Key;
import com.signalsin.jetpacking.elements.Player;
import com.signalsin.jetpacking.elements.WorldRenderer;
import com.signalsin.jetpacking.resources.ImageCache;
import com.signalsin.jetpacking.resources.Sounds;


public class GameScreen implements Screen {
	
	JetPackingGame game;

	SpriteBatch spriteBatch;
	
	OrthographicCamera worldCamera;
	OrthographicCamera guiCamera;
	
	Rectangle glViewport;
	
	Player player;
	
	Controls controls;
	
	Vector3 touchPoint = new Vector3();
	
	Key key;
	
	WorldRenderer world;
	
	float playerX;
	float playerY;
	
	float playerStateTime;
	
    // constructor to keep a reference to the main Game class
    public GameScreen(JetPackingGame JetPackingGame){
            this.game = JetPackingGame;
    }
    
    public void onCreate(){
		Gdx.app.log("MyLibGDXGame", "GameLevel.create()");
		
		//load everything needed at the start of a new instance of GameScreen
		ImageCache.load("pack");
		Sounds.load();

		worldCamera = new OrthographicCamera(Config.VIRTUAL_WIDTH, Config.VIRTUAL_HEIGHT);
		worldCamera.setToOrtho(false);
		worldCamera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		
		guiCamera = new OrthographicCamera(Config.VIRTUAL_WIDTH, Config.VIRTUAL_HEIGHT);
		guiCamera.setToOrtho(false);
		worldCamera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		
		//set the initial player co-ordinates
		playerX = Config.VIRTUAL_WIDTH / 2;
		playerY = Config.VIRTUAL_HEIGHT / 2;
		
		//initialise a new player with the player co-ordinates
		player = new Player(playerX,playerY);
		
		//initialise the key object
		key = new Key(50, 200);
		
		//Initialise the controls
		controls = new Controls();
		
		//initialise a new spritebatch
	    spriteBatch = new SpriteBatch();
	    
	    glViewport = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    
	    //intialise world renderer, passing a level number
	    world = new WorldRenderer(1);
	    
    }
	
	@Override
	public void render(float arg0) {
		
		//clear the screen
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		gl.glViewport((int) glViewport.x, (int) glViewport.y,
                (int) glViewport.width, (int) glViewport.height);
		
		worldCamera.update();
		worldCamera.apply(gl);
		
		
		//check if the touch screen is touched
		onTouch();
		
		//reset onPlatform
		player.setOnPlatform(false);
		
		player.update();
		
		//check collision with key
		if (player.getBounds().overlaps(key.getBounds()) && (key.getVisibility() == true)){
			key.setVisibility(false);
			player.setHasKey(true);
			Sounds.play(Sounds.key);
		}
		
		//check collision with each platform object
		checkCollisions();    
        
        worldCamera.position.set(player.getPosition().x, player.getPosition().y, 0);
        
        
        //aligns the spriteBatch to the world camera
        //so that coordinates are in level space, not screen
        spriteBatch.setProjectionMatrix(worldCamera.combined);
        
		//start the batcher, so we would want to do all of our draw calls between batcher.begin and .end
		spriteBatch.begin();
		
		//draw all platforms
		world.draw(spriteBatch);
		
		//draw the key object if the player hasn't already got it
		key.draw(spriteBatch);
		
		//draw the player
		player.draw(spriteBatch);
		spriteBatch.end();
		
        spriteBatch.setProjectionMatrix(guiCamera.combined);
        
		spriteBatch.begin();
		
		//draw the controls
		controls.draw(spriteBatch, guiCamera);
		
		spriteBatch.end();
		
		//Gdx.app.log("FPS: ", String.valueOf(Gdx.graphics.getFramesPerSecond()));
		//Gdx.app.log("State: ", String.valueOf(Controls.state));
		//Gdx.app.log("player bounds y: ", String.valueOf(player.getBounds().y));
	}

	@Override
	public void dispose() {
		Gdx.app.log("MyLibGDXGame", "GameScreen.dispose()");
		ImageCache.dispose();
		Sounds.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		//this method is called immediately before the game is closed
		//it would be a good idea to place any save methods here
		Gdx.app.log("MyLibGDXGame", "GameScreen.pause()");
	}

	
	@Override
	public void resize(int width, int height) {
		Gdx.app.log("MyLibGDXGame", "GameLevel.resize()");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	private void onTouch(){
		//check if a control has been touched
		//call the appropriate movement method
		
		if (Controls.state.contains("leftArrow")){
			player.moveLeft();
		}
		else if (Controls.state.contains("rightArrow")){
			player.moveRight();
		}
		
		if (Controls.state.contains("button")){
			player.setVelocity(Gdx.graphics.getDeltaTime() * player.getSpeed());
			player.setIsFalling(false);
		}
		else {
			player.setVelocity(-(Gdx.graphics.getDeltaTime() * player.getSpeed()));
			
		}
		
	}
	
	private void checkCollisions(){
		
		for (int i=0; i<world.getTotalPlatforms(); i++){
			
			//check for collision
			if (player.getBounds().overlaps(world.getPlatform(i).getBounds())){
				
				//check if player is falling and platform underneath
				if (player.getIsFalling() && (player.getPosition().y > world.getPlatform(i).getBounds().y)){
					player.setY(world.getPlatform(i).getBounds().y + world.getPlatform(i).getBounds().height);
					player.setOnPlatform(true);
				}
				
				//else if the player is moving upwards and under the platform
				else if ((player.getIsFalling() == false) 
						&& (((player.getBounds().y + (player.getBounds().height - 5) ) < world.getPlatform(i).getBounds().y))) {
					
					player.setY(world.getPlatform(i).getBounds().y - player.getBounds().height);
				}
				
				//if platform to right
				else if (player.getFacing() == "right") {
					player.setX(world.getPlatform(i).getBounds().x - player.getBounds().width);	
				}
				
				//if platform to left
				else if (player.getFacing() == "left") {
					player.setX(world.getPlatform(i).getBounds().x + world.getPlatform(i).getBounds().width);
				}
			}
			
		}
		
	}
	
}
