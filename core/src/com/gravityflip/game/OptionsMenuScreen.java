package com.gravityflip.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gravityflip.UI.GameButton;

public class OptionsMenuScreen extends BaseScreen {

    Table UITable;
    BitmapFont TextStyle;

    Label OptionsText = new Label("Hello World",);
    GameButton ReturnButton = new GameButton(" Button_4.png");
    Label.LabelStyle FontStyle = new Label.LabelStyle()

    OptionsMenuScreen(){
        super();

        UITable = new Table();
        UITable.setFillParent(true);
        UITable.setDebug(true);

        ReturnButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                EngineClass.GetInstance().LoadScene(new MainMenuScreen());
                return false;
            }
        });

        UITable.add(ReturnButton);

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
