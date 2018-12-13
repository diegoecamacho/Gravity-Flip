package com.gravityflip.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.gravityflip.BaseScreen;

public class MainMenuScreen extends BaseScreen {

    Table UITable;
    Skin SceneSkin;
    TextButton PlayButton;
    TextButton OptionsButtom;
    TextButton ScoreButton;


    MainMenuScreen(){
        super();
        UITable = new Table();
        UITable.columnDefaults(0).expand();
        UITable.columnDefaults(1).expand();
        UITable.columnDefaults(2).expand();
        UITable.setFillParent(true);
        UITable.setDebug(true);

        SceneSkin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));

        PlayButton = new TextButton("Play", SceneSkin);
        OptionsButtom = new TextButton("Options", SceneSkin);
        ScoreButton = new TextButton("Score", SceneSkin);

        PlayButton.getLabel().setFontScale(4);
        OptionsButtom.getLabel().setFontScale(2);
        ScoreButton.getLabel().setFontScale(4);

        PlayButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                EngineClass.GetInstance().LoadScene(new GameOverScreen());
                return false;
            }
        });

        OptionsButtom.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                EngineClass.GetInstance().LoadScene(new OptionsMenuScreen());
                return false;
            }
        });

        ScoreButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                EngineClass.GetInstance().LoadScene(new HighScoreScreen());
                return false;
            }
        });

        UITable.add(OptionsButtom).height(Value.percentHeight(0.1F, UITable)).width(Value.percentHeight(0.1F, UITable)).left().padLeft(100);
        UITable.row();
        UITable.add().height(Value.percentWidth(0.2F, UITable)).maxSize(100,100); // Title
        UITable.row();
        UITable.add(PlayButton).width(Value.percentWidth(0.2F, UITable)).height(Value.percentWidth(0.05F, UITable));
        UITable.row();
        UITable.add(ScoreButton).width(Value.percentWidth(0.2F, UITable)).height(Value.percentWidth(0.05F, UITable));

        mainStage.addActor(UITable);
    }

    @Override
    public void Initialize() {

    }

    @Override
    public void Update(float dt) {

    }


    @Override
    public void dispose() {
        super.dispose();
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
