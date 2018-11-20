package com.gravityflip.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

/**
 * Root Code for Gravity Flip the Game
 */
public class EngineClass extends Game {
	public static EngineClass engine;

	BaseScreen activeScreen;

	EngineClass(){
		if (engine == null){
			engine = this;
		}
	}

	/**
	 *<p>Loads the passed Screen</p>
	 *<@param>scene</@param>
	 */
	public void LoadScene(BaseScreen scene){
        if (activeScreen != null)
            activeScreen.dispose();

        activeScreen = scene;
        this.setScreen(activeScreen);

	}

    /**
     * <P>Get Current Instance of Engine</P>
     * @return Engine Class
     */
	public static EngineClass GetInstance(){
	    return  engine;
    }


	@Override
	public void create () {
	    InputMultiplexer inputMultiplexer = new InputMultiplexer();
	    Gdx.input.setInputProcessor(inputMultiplexer);
        LoadScene(new SplashScreen());
	}


	@Override
	public void dispose () {
        activeScreen.dispose();
	    engine.dispose();

	}
}
