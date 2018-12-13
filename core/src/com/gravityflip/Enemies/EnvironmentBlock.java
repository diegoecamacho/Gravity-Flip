package com.gravityflip.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gravityflip.ActorBase;

/**
 * <p>Base Class for all enemies</p>
 */
public class EnvironmentBlock extends ActorBase {

    public   EnvironmentBlock(){
        super();
        worldBound = false;
    }

    public EnvironmentBlock(String filename){
        super();
        loadTexture(filename);
        setBoundaryRectangle();
        worldBound = false;
    }

    public EnvironmentBlock(String filename, Stage stage){
        super();
        loadTexture(filename);
        setBoundaryRectangle();
        stage.addActor(this);
        worldBound = false;
    }




}
