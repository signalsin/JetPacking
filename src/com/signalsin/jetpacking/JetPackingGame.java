package com.signalsin.jetpacking;

import com.badlogic.gdx.Game;
import com.signalsin.jetpacking.screens.GameScreen;
import com.signalsin.jetpacking.screens.MainMenuScreen;

public class JetPackingGame extends Game {
	
	//declare all of the games screens
	GameScreen gameScreen;
	MainMenuScreen mainMenuScreen;
	
	@Override
	public void create() {
		
		//load the screens
        mainMenuScreen = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);
        
        //call the onCreate function to do all of the stuff it needs to before rendering
        //ie declaring variables, loading images and sounds etc
        gameScreen.onCreate();
        setScreen(gameScreen);
	}

}
