package com.gravityflip.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.gravityflip.BaseScreen;
import com.gravityflip.Enemies.MissileActor;
import com.gravityflip.PlayerClass;

public class GameScene extends BaseScreen {

    final Skin SceneSkin =  new Skin(Gdx.files.internal("default/skin/uiskin.json"));
    //final Skin CustomSkin =  new Skin(Gdx.files.internal("default/DIEGO/Pause.json"));
    Table UITable;

    TextButton PauseButton;

    Texture BackgroundImage = new Texture(Gdx.files.internal("Background.jpg"));

   // TextureRegion blockTexture = new TextureRegion(new Texture("platformIndustrial.jpg"));

    public PlayerClass getPlayerActor() {
        return playerActor;
    }

    PlayerClass playerActor;
    boolean isPositive;

    MissileActor missileActor;

    public GameScene() {
        super();
        UITable = new Table();
        UITable.setDebug(true);
        UITable.columnDefaults(0).expandX();
        UITable.columnDefaults(1).expand();
        UITable.setFillParent(true);

        PauseButton = new TextButton("Options", SceneSkin);
        PauseButton.getLabel().setFontScale(3);

        UITable.add(PauseButton).left().height(200).width(200).padLeft(50).padTop(100);
        UITable.row();
        UITable.add().expand();

        playerActor = new PlayerClass();
        playerActor.setScale(3.0f);
        playerActor.setOrigin(playerActor.getX()/2,playerActor.getY()/2);
        playerActor.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        playerActor.setWorldBounds(mainStage.getWidth(),mainStage.getHeight());
        playerActor.boundToWorld();
        //playerActor.setAcceleration(500);

        missileActor = new MissileActor();
        missileActor.setScale(0.1f);
        missileActor.setPosition(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/2);



        isPositive = true;


        mainStage.addActor(UITable);
        mainStage.addActor(playerActor);
        mainStage.addActor(missileActor);
    }

    @Override
    public void Initialize() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

       playerActor.gravityAngle *= -1;
       if(isPositive) {
           isPositive = false;
           playerActor.setAnimation(playerActor.negativeCharge);
          // playerActor.setScale(3.0f);
       }
       else {
           isPositive = true;
           playerActor.setAnimation(playerActor.positiveCharge);
         //  playerActor.setScale(3.0f);
       }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public void Update(float dt) {
        mainStage.act();
        for(Actor actors: mainStage.getActors()){
            if(actors.getX() < 0 - actors.getWidth()){
                actors.remove();
            }
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
       // mainStage.act();

        mainStage.getBatch().begin();
        mainStage.getBatch().draw(BackgroundImage,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainStage.getBatch().end();
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
