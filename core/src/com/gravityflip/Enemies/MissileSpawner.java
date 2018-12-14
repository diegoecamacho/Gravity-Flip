package com.gravityflip.Enemies;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import static com.gravityflip.BaseScreen.SCREENHEIGHT;
import static com.gravityflip.BaseScreen.SCREENWIDTH;

public class MissileSpawner extends Actor {
    public MissileActor DefaultMissile;

    EnviromentBlockSpawner blockSpawner;
    Array<MissileActor> Missiles;

    int MaxScreenMissileSteps;

    float SpanwLocationX;

    MissileSpawner(MissileActor defaultMissile,EnviromentBlockSpawner spawner){
        Missiles = new Array<MissileActor>();
        DefaultMissile = defaultMissile;
        blockSpawner = spawner;


        SpanwLocationX = SCREENWIDTH + spawner.DefaultBlock.getHeight();
        MaxScreenMissileSteps = MathUtils.round(SCREENHEIGHT/blockSpawner.DefaultBlock.getHeight());

        GenerateMissiles();
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        CheckOutOfBounds();
    }

    void GenerateMissiles(){
        for (int x =0 ; x < MaxScreenMissileSteps; x++){
            MissileActor missileActor = new MissileActor();
            missileActor.setPosition(SpanwLocationX, SCREENHEIGHT/MaxScreenMissileSteps * x);
            Missiles.add(missileActor);
        }
    }

    public void SpawnMissiles(){
        for (MissileActor missile : Missiles) {
            if (!missile.isAlive){
                missile.setPosition(SpanwLocationX, SCREENHEIGHT/MaxScreenMissileSteps * MathUtils.random(MaxScreenMissileSteps));
                blockSpawner.AddBlockToScene(missile);
                missile.SetAlive(true);
                break;
            }
        }
    }

    private void CheckOutOfBounds(){
        for (MissileActor missile: Missiles) {
            if (missile.getX() < 0 - blockSpawner.DefaultBlock.getHeight()){
                missile.SetAlive(false);
                missile.setPosition(SpanwLocationX , 0);
            }
            if (missile.getY() > SCREENWIDTH){
                missile.SetAlive(false);
                missile.setPosition(SpanwLocationX , 0);
            }
        }
    }
}
