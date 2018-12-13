package com.gravityflip.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.gravityflip.BaseScreen;
import com.gravityflip.Particle1;
import com.gravityflip.Particle2;
import com.gravityflip.Particle3;
import com.gravityflip.PlayerActor;


public class OptionsMenuScreen extends BaseScreen {

    Skin sceneSkin;
    Table UITable;
    Label MenuTitle;
    Label AudioText;
    Slider AudioSlider;
    TextButton ReturnButton;
    Particle2 menuParticle;
    Sound clickSound;


    public OptionsMenuScreen(){
        super();
        UITable = new Table();
        UITable.columnDefaults(0).expand(true,true);
        UITable.columnDefaults(1).expand(true,true);
        UITable.columnDefaults(2).expand(true,true);
        UITable.setFillParent(true);
        UITable.setDebug(true);

        PlayerActor backgroundImage = new PlayerActor(0,0,mainStage);
        backgroundImage.loadTexture("magneticBG.png");
        backgroundImage.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        sceneSkin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        MenuTitle = new Label("Options Menu", sceneSkin);
        AudioSlider = new Slider(0,1,0.1f,false, sceneSkin);
        Container<Slider> sliderContainer = new Container<Slider>(AudioSlider);
        sliderContainer.setTransform(true);
        sliderContainer.scaleBy(5);
        ReturnButton = new TextButton("Main Menu", sceneSkin);

        MenuTitle.setFontScale(5);
        MenuTitle.setAlignment(Align.center);

        ReturnButton.getLabel().setFontScale(4);

        menuParticle = new Particle2();
        menuParticle.start();
        menuParticle.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/5);
        menuParticle.setScale(10.0f);
        mainStage.addActor(menuParticle);

        clickSound = Gdx.audio.newSound(Gdx.files.internal("clickSound.mp3"));
        clickSound.setVolume(1,0.25f);



        ReturnButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                clickSound.play();
                EngineClass.GetInstance().LoadScene(new MainMenuScreen());
                return false;
            }
        });

        UITable.add(MenuTitle).padTop(15).width(Value.percentWidth(.5f,UITable)).height(Value.percentHeight(0.2f,UITable)).center();
        UITable.row();
        UITable.add(sliderContainer).padRight(400);
        UITable.row();
        UITable.add(ReturnButton).width(Value.percentWidth(0.2f,UITable)).height(Value.percentHeight(0.1f,UITable));

        mainStage.addActor(UITable);

    }

    @Override
    public void Initialize() {

    }

    @Override
    public void Update(float dt) {
        EngineClass.GetInstance().MASTERSOUNDLEVEL = AudioSlider.getValue();
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
