package com.gravityflip;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.gravityflip.ActorBase;
import com.gravityflip.game.EngineClass;


public class PlayerClass extends PlayerActor {

    public Animation<TextureRegion> positiveCharge;
    public Animation<TextureRegion> negativeCharge;
    public float gravityAngle;

    public PlayerClass(){
        String[] blueAnimString = {"blue0.png","blue1.png","blue2.png","blue3.png"};
        String[] redAnimString = {"red0.png","red1.png","red2.png","red3.png"};
        positiveCharge = loadAnimationFromFiles(blueAnimString, 0.25f, true);
        negativeCharge = loadAnimationFromFiles(redAnimString, 0.25f, true);
        this.setBoundaryRectangle();
        this.setOrigin(getX()/2,getY()/2);
        setScale(3.0f);
        setMaxSpeed(650);
        gravityAngle = -90;
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        setAcceleration(800);
        accelerateAtAngle(gravityAngle);
        applyPhysics(dt);

        boundToWorld();
    }

}
