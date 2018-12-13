package com.gravityflip.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gravityflip.PlayerActor;

/**
 * Created by user on 12/12/2018.
 */

public class MissileActor extends PlayerActor {

    public Animation<TextureRegion> moving;
    public float moveAngle;
    public boolean isAlive;

    public MissileActor(){
        String[] movingAnimString = {"missile1.png","missile2.png","missile3.png","missile2.png"};
        moving = loadAnimationFromFiles(movingAnimString,0.25f,true);
        this.setOrigin(getX()/2, getY()/2);
        this.setBoundaryRectangle();
        this.setScale(0.1f);
        setMaxSpeed(300);

    }

    @Override
    public void act(float dt) {
        super.act(dt);
        setAcceleration(200);
        accelerateAtAngle(180);
        applyPhysics(dt);
    }
}
