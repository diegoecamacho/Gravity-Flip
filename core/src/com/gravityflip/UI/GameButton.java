package com.gravityflip.UI;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class GameButton extends Button{

    public GameButton(String InternalPath){
        super(new TextureRegionDrawable(new TextureRegion(new Texture(InternalPath))));
    }

    public GameButton(Skin skin){
        super(skin);
    }

}

/*public abstract class BaseButton extends Actor{
        Texture sprite;
        Rectangle collider;

        int imgX, imgY;
    public BaseButton(String InternalPath , int scale){
            sprite = new Texture(InternalPath);
            imgX = sprite.getWidth() * scale;
            imgY = sprite.getHeight() * scale;

            setWidth(imgX);
            setHeight(imgY);

            collider = new Rectangle((int)getX(),(int)getY(),imgX ,imgY);



        }

        public void OnTouchDown(int x , int y){
            if (isOverlap(x,y)){
                Gdx.app.log("IsOverlap", "Overlap");
                Activate();
            }
        }

        boolean isOverlap(int x, int y){
            boolean overlap = collider.contains(x,y);
            Gdx.app.log("Overlapping", "Overlap: " + overlap);
            return overlap;
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            Gdx.app.log("Image Size", "X: " + imgX + "Y: " + imgY);
            Gdx.app.log("Rec Pos", "X: " + getX() + "Y: " + getY());
        }

        @Override
        public void setPosition(float x, float y) {
            super.setPosition(x, y);
            collider.setPosition(x,y);
        }

        public abstract void Activate();



        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            batch.draw(sprite,(int)getX(),(int)getY() - imgY ,imgX, imgY);
        }
}*/


