package com.gravityflip.Enemies;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.gravityflip.BaseScreen.SCREENHEIGHT;
import static com.gravityflip.BaseScreen.SCREENWIDTH;

public class EnviromentBlockSpawner extends Actor {

    String DefaultBlockSprite;
    EnvironmentBlock DefaultBlock;
    EnvironmentBlock NewestBlock;
    public Array<EnvironmentBlock> activeBlocks;
    Stage targetStage;

    public float blockSpeed = -1;

    private float SpawnX;

    


    Vector2 spawnPositionsTop;
    Vector2 spawnPositionsBottom;

    private int MaxStoneStepRatio;
    private int topStoneStepRation;
    private int BottomStoneStepRation;
    int RandomizeYAfter = 3;

    /*
    Range in which blocks can spawn
     */
    private int range = 1;
    /*
    The saws will have a 1 in sawSpawnChance to spawn a saw.
     */
    private int fullSawSpawnChange = 10;
    private int halfSawSpawnChance = 5;



    private int bottomOffSet = 3;
    private int yUpdateCount = 0;


    private int minRandomYRange = 3;
    private int maxRandomYRange = 6;


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

        MaxStoneStepRatio = MathUtils.round(SCREENHEIGHT / DefaultBlock.getHeight());
        SpawnX = SCREENWIDTH + DefaultBlock.getWidth();

        spawnPositionsBottom = new Vector2(SpawnX, SCREENHEIGHT/15 * 11);
        spawnPositionsTop = new Vector2(SpawnX, SCREENHEIGHT/15 * 2 );


        CreateNewPair();


    }

    private void RandomizeYs(){
        BottomStoneStepRation = bottomOffSet + MathUtils.random(-range,range);

        int remainingStoneSteps = MaxStoneStepRatio - BottomStoneStepRation - bottomOffSet;
        topStoneStepRation = BottomStoneStepRation + bottomOffSet + random(bottomOffSet,remainingStoneSteps);

    }

    public void SetEnviromentalBlock(EnvironmentBlock block){
        DefaultBlock = block;
        SpawnX = SCREENWIDTH + DefaultBlock.getWidth();
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        moveBlocks();
       if (NewestBlock.getX() < SCREENWIDTH){
           if (yUpdateCount >= RandomizeYAfter )
           {
               RandomizeYs();
               RandomizeYAfter = MathUtils.random(minRandomYRange,maxRandomYRange);
               yUpdateCount = 0;
           }
            CreateNewPair();
           yUpdateCount++;
       }
       CheckOutOfBounds();
    }

    void CreateNewPair(){
        EnvironmentBlock TopBlock = new EnvironmentBlock(DefaultBlockSprite);
        EnvironmentBlock BotBlock =  new EnvironmentBlock(DefaultBlockSprite);

        spawnPositionsTop  = new Vector2(SpawnX, SCREENHEIGHT/MaxStoneStepRatio * topStoneStepRation);
        spawnPositionsBottom  = new Vector2(SpawnX, SCREENHEIGHT/MaxStoneStepRatio * BottomStoneStepRation);

        GenerateHalfSaw();

        GenerateFullSaw();
        
        

        TopBlock.setPosition(spawnPositionsTop.x ,spawnPositionsTop.y);
        BotBlock.setPosition(spawnPositionsBottom.x ,spawnPositionsBottom.y);


        activeBlocks.add(TopBlock);
        activeBlocks.add(BotBlock);

        NewestBlock = TopBlock;

        targetStage.addActor(TopBlock);
        targetStage.addActor(BotBlock);
    }

    private void GenerateFullSaw(){
        if (random(fullSawSpawnChange) == 0) {
            int sawStepRange = topStoneStepRation - BottomStoneStepRation;
            int sawStep = MathUtils.random(sawStepRange);

            EnviromentFullSaw saw = new EnviromentFullSaw();
            saw.setPosition(SpawnX, SCREENHEIGHT / MaxStoneStepRatio * (BottomStoneStepRation + sawStep));
            activeBlocks.add(saw);
            targetStage.addActor(saw);
        }

    }
    

    private void GenerateHalfSaw() {
        if (random(halfSawSpawnChance) == 0){
           int random = random(2);
           if (random == 0){
                EnvironmentHalfSaw saw = new EnvironmentHalfSaw();
                saw.FlipCurrentAnim();
                saw.setPosition(SpawnX, SCREENHEIGHT/MaxStoneStepRatio * (topStoneStepRation - 1) );
                activeBlocks.add(saw);
                targetStage.addActor(saw);

           }
            else {
                EnvironmentHalfSaw saw = new EnvironmentHalfSaw();
                saw.setPosition(SpawnX, SCREENHEIGHT/MaxStoneStepRatio * (BottomStoneStepRation + 1));
                activeBlocks.add(saw);
                targetStage.addActor(saw);
            }
       }
    }

    private void moveBlocks(){
        for (int x = 0 ; x < activeBlocks.size; x++){
            activeBlocks.get(x).moveBy(blockSpeed,0);
        }
    }

    private void CheckOutOfBounds(){
        for (EnvironmentBlock block: activeBlocks) {
            if (block.getX() < 0 - DefaultBlock.getWidth()){
                block.remove();
                activeBlocks.removeValue(block, true);
            }
        }
    }


}
