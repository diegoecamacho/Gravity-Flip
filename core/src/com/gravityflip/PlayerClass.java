package com.gravityflip;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class PlayerClass extends PlayerActor {

    public Animation<TextureRegion> positiveCharge;
    public Animation<TextureRegion> negativeCharge;
    public float gravityAngle;

    final String[] blueAnimString = {"blue0.png","blue1.png","blue2.png","blue3.png"};
    final String[] redAnimString = {"red0.png","red1.png","red2.png","red3.png"};

    public PlayerClass(){
        this.loadAnimationFromFiles("blueAnimString",blueAnimString, 0.25f, true);
        this.loadAnimationFromFiles("redAnimString",redAnimString, 0.25f, true);
        this.setBoundaryRectangle();
        this.setOrigin(getX()/2,getY()/2);
        setScale(2.0f);
        setMaxSpeed(750);
        gravityAngle = -90;
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        setAcceleration(800);
        accelerateAtAngle(gravityAngle);
        applyPhysics(dt);

       // boundToWorld();
    }

}
