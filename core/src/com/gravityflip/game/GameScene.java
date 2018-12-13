package com.gravityflip.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.gravityflip.BaseScreen;
import com.gravityflip.Enemies.EnviromentBlockSpawner;
import com.gravityflip.Enemies.EnvironmentBlock;

public class GameScene extends BaseScreen {

    final Skin SceneSkin =  new Skin(Gdx.files.internal("default/skin/uiskin.json"));
    //final Skin CustomSkin =  new Skin(Gdx.files.internal("default/DIEGO/Pause.json"));
    Table UITable;

    TextButton PauseButton;

    Texture BackgroundImage = new Texture(Gdx.files.internal("Background.jpg"));

    Texture BrickTexture = new Texture(Gdx.files.internal("platformIndustrial.png"));

    EnviromentBlockSpawner enviromentBlockSpawner;

    EnvironmentBlock playerActor;

    public GameScene() {
        super();
        UITable = new Table();
        UITable.setDebug(true);
        UITable.columnDefaults(0).expandX();
        UITable.columnDefaults(1).expand();
        UITable.setFillParent(true);

        enviromentBlockSpawner = new EnviromentBlockSpawner("platformIndustrial.png", mainStage);


        PauseButton = new TextButton("Options", SceneSkin);
        PauseButton.getLabel().setFontScale(3);


     ControllerTable.add(PauseButton).left().padLeft(200).padBottom(100);




        mainStage.addActor(enviromentBlockSpawner);
        mainStage.addActor(ControllerTable);

    }

    @Override
    public void Initialize() {

    }

    @Override
    public void Update(float dt) {
        mainStage.act(dt);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

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
