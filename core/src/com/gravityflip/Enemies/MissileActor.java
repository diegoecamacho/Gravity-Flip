package com.gravityflip.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gravityflip.PlayerActor;

/**
 * Created by user on 12/12/2018.
 */

public class MissileActor extends EnvironmentBlock {

    String[] movingAnimString = {"missile1.png","missile2.png","missile3.png","missile2.png"};
    public Animation<TextureRegion> moving;
    public boolean isAlive = false;

    public MissileActor(){
        BlockType = Type.Missile;
        loadAnimationFromFiles("Move",movingAnimString,0.25f,true);
        this.setOrigin(getX()/2, getY()/2);
        this.setBoundaryRectangle();
        this.setScale(0.1f);
        setMaxSpeed(300);

    }

    public void SetAlive(boolean alive){
        isAlive = alive;
    }

    @Override
    public void act(float dt) {
        if(isAlive) {
            setAcceleration(200);
            accelerateAtAngle(180);
            applyPhysics(dt);
        }
    }
}
