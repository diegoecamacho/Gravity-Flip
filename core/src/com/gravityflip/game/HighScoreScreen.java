package com.gravityflip.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.gravityflip.BaseScreen;
import com.gravityflip.Particle2;
import com.gravityflip.Particle3;
import com.gravityflip.PlayerActor;

public class HighScoreScreen extends BaseScreen {
    Table UITable;
    Skin SceneSkin;
    Label HighScoreText;
    Label PreviousScore;
    TextButton MainMenuButton;
    Sound clickSound;

    Particle3 menuParticle;

    HighScoreScreen()
    {
        super();

        UITable = new Table();
        UITable.columnDefaults(0).expand();
        UITable.columnDefaults(1).expand();
        UITable.columnDefaults(2).expand();
        UITable.setFillParent(true);
        UITable.setDebug(false);


        PlayerActor backgroundImage = new PlayerActor(0,0,mainStage);
        backgroundImage.loadTexture("magneticBGred.png");
        backgroundImage.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        clickSound = Gdx.audio.newSound(Gdx.files.internal("clickSound.mp3"));
        clickSound.setVolume(1,0.25f);

        menuParticle = new Particle3();
        menuParticle.start();
        menuParticle.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/5);
        menuParticle.setScale(10.0f);
        mainStage.addActor(menuParticle);

        SceneSkin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));

        HighScoreText = new Label("High Score Menu" , SceneSkin);

        MainMenuButton = new TextButton("Main Menu", SceneSkin);

        PreviousScore = new Label("Previous Score: " + Integer.toString( EngineClass.GetInstance().CURRENTSCORE),SceneSkin);

        HighScoreText.setFontScale(8);
        PreviousScore.setFontScale(6);
        MainMenuButton.getLabel().setFontScale(4);

        MainMenuButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                clickSound.play();
                EngineClass.GetInstance().LoadScene(new MainMenuScreen());
                return false;
            }
        });

        UITable.add(HighScoreText);
        UITable.row();
        UITable.add(PreviousScore);
        UITable.row();
        UITable.add(MainMenuButton);

        mainStage.addActor(UITable);
        mainStage.addActor(menuParticle);
    }

    @Override
    public void Initialize() {

    }

    @Override
    public void Update(float dt) {

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
