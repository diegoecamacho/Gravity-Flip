package com.gravityflip.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.gravityflip.UI.GameButton;

import javax.swing.text.html.Option;

public class MainMenuScreen extends BaseScreen {

    Table UITable;

    GameButton PlayButton = new GameButton("Button_1.png");
    GameButton OptionsButtom = new GameButton("Button_2.png");
    GameButton ScoreButton = new GameButton("Button_3.png");


    MainMenuScreen(){
        super();

        UITable = new Table();
        UITable.setFillParent(true);
        UITable.setDebug(true);

        PlayButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                EngineClass.GetInstance().LoadScene(new OptionsMenuScreen());
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
                EngineClass.GetInstance().LoadScene(new OptionsMenuScreen());
                return false;
            }
        });

        UITable.add(OptionsButtom).expandX().width(Value.percentHeight(0.1f,UITable))
                .height(Value.percentHeight(0.1f,UITable)).left().top().padLeft(50);

        UITable.row();
        UITable.add().expandX().height(Value.percentHeight(0.3f,UITable)); // Title
        UITable.row();
        UITable.add(PlayButton).width(Value.percentWidth(0.2f,UITable)).height(Value.percentHeight(0.15f,UITable)).colspan(2).padTop(10);
        UITable.row();
        UITable.add(ScoreButton).width(Value.percentWidth(0.2f,UITable)).height(Value.percentHeight(0.15f,UITable)).colspan(2).padTop(10);
        UITable.add();

        mainStage.addActor(UITable);
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
