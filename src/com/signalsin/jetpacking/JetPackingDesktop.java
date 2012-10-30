package com.signalsin.jetpacking;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class JetPackingDesktop {
	
	
	public static void main(String[] args) 
	{
		//Create a new LwjgjApplication, which we need to pass our main GAME class when it is finished
		new LwjglApplication(new JetPackingGame(), "JetPacking", 320, 480, false);
	}
}