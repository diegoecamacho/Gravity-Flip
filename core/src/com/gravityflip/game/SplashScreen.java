package com.gravityflip.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
        import com.badlogic.gdx.scenes.scene2d.ui.Skin;
        import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gravityflip.BaseScreen;


public class SplashScreen extends BaseScreen {
    Table UITable;

    Skin SceneSkin;

    Label SplashText;
    Texture SplashImage;

    float timeElapsed;
    final float TOTALLOADSCREENTIME = 3f;

    SplashScreen()
    {
        super();

        UITable = new Table();
        UITable.columnDefaults(0).expand();
        UITable.columnDefaults(1).expand();
        UITable.columnDefaults(2).expand();
        UITable.setFillParent(true);
        UITable.setDebug(true);

        SceneSkin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));

        SplashText = new Label("Splash Screen" , SceneSkin);
        SplashImage = new Texture(Gdx.files.internal("splashLogoGrey.png"));

        SplashText.setFontScale(8);

        UITable.add();
        UITable.row();
        UITable.add();
        UITable.row();
        UITable.add();

        mainStage.addActor(UITable);

    }

    @Override
    public void Initialize() {

    }

    @Override
    public void Update(float dt) {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        timeElapsed += delta;
        if (timeElapsed >= TOTALLOADSCREENTIME) {
            EngineClass.GetInstance().LoadScene(new MainMenuScreen());
        }
        mainStage.getBatch().begin();
        mainStage.getBatch().draw(SplashImage,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainStage.getBatch().end();
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
