package com.gravityflip.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.utils.Array;

import static com.gravityflip.BaseScreen.SCREENHEIGHT;
import static com.gravityflip.BaseScreen.SCREENWIDTH;

public class EnviromentBlockSpawner extends Actor {

    String DefaultBlockSprite;
    EnvironmentBlock DefaultBlock;
    EnvironmentBlock NewestBlock;
    Array<EnvironmentBlock> activeBlocks;
    Stage targetStage;

    private float SpawnX;

    Vector2 spawnPositionsTop;
    Vector2 spawnPositionsBottom;


    public EnviromentBlockSpawner(Stage targetStage){
        this.targetStage = targetStage;
        Initiazize();
    }

    public EnviromentBlockSpawner(String defaultBlocksprite, Stage targetStage) {
        this.targetStage = targetStage;
        this.DefaultBlockSprite= defaultBlocksprite;
        Initiazize();
    }

    void Initiazize(){
        activeBlocks = new Array<EnvironmentBlock>();
        DefaultBlock = new EnvironmentBlock(DefaultBlockSprite);

        SpawnX = SCREENWIDTH + DefaultBlock.getWidth();

        spawnPositionsBottom = new Vector2(SpawnX, SCREENHEIGHT/15 * 11);
        spawnPositionsTop = new Vector2(SpawnX, SCREENHEIGHT/15 * 2 );


        CreateNewPair();


    }

    public void SetEnviromentalBlock(EnvironmentBlock block){
        DefaultBlock = block;
        SpawnX = SCREENWIDTH + DefaultBlock.getWidth();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBlocks();
       if (NewestBlock.getPosition().x < SCREENWIDTH){
            CreateNewPair();
       }

    }

    void CreateNewPair(){
        EnvironmentBlock TopBlock = new EnvironmentBlock(DefaultBlockSprite);
        EnvironmentBlock BotBlock =  new EnvironmentBlock(DefaultBlockSprite);

        TopBlock.setPosition(spawnPositionsTop.x ,spawnPositionsTop.y);
        BotBlock.setPosition(spawnPositionsBottom.x ,spawnPositionsBottom.y);

        activeBlocks.add(TopBlock);
        activeBlocks.add(BotBlock);

        NewestBlock = TopBlock;

        targetStage.addActor(TopBlock);
        targetStage.addActor(BotBlock);
    }

    private void moveBlocks(){
        for (int x = 0 ; x < activeBlocks.size; x++){
            activeBlocks.get(x).moveBy(-5,0);
        }
    }
}
