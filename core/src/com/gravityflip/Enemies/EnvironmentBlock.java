package com.gravityflip.Enemies;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gravityflip.PlayerActor;

/**
 * <p>Base Class for all enemies</p>
 */
public class EnvironmentBlock extends PlayerActor {

    public enum Type{
        Enviroment,
        EnviromentHazard,
        Missile
    }

    String FileName;

    public Type BlockType;

    public   EnvironmentBlock(){
        super();
       // worldBound = false;
        BlockType = Type.Enviroment;
        this.setBoundaryRectangle();
    }

    public EnvironmentBlock(String filename){
        super();
        FileName = filename;
        loadTexture(FileName);
        this.setBoundaryRectangle();
        BlockType = Type.Enviroment;
       // worldBound = false;
    }

    public EnvironmentBlock(String filename, Stage stage){
        super();
        FileName = filename;
        loadTexture(filename);
        this.setBoundaryRectangle();
        stage.addActor(this);
        BlockType = Type.Enviroment;
       // worldBound = false;
    }

    public EnvironmentBlock(EnvironmentBlock block){
        FileName = block.FileName;
        loadTexture(FileName);
        this.setBoundaryRectangle();
        BlockType = Type.Enviroment;
    }


}
