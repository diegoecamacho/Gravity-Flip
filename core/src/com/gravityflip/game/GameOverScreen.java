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
import com.gravityflip.PlayerActor;

public class GameOverScreen extends BaseScreen {
    Table UITable;

    Skin SceneSkin;

    Label HighScoreText;
    TextButton MainMenuButton;
    PlayerActor titleLogo;
    Sound clickSound;
    public GameOverScreen()
    {
        super();

        UITable = new Table();
        UITable.columnDefaults(0).expand();
        UITable.columnDefaults(1).expand();
        UITable.columnDefaults(2).expand();
        UITable.setFillParent(true);
        UITable.setDebug(false);


        SceneSkin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));

        HighScoreText = new Label("RoundScore: " + Integer.toString( EngineClass.GetInstance().CURRENTSCORE), SceneSkin);
        MainMenuButton = new TextButton("Main Menu", SceneSkin);

        HighScoreText.setFontScale(8);
        MainMenuButton.getLabel().setFontScale(4);

        PlayerActor backgroundImage = new PlayerActor(0,0,mainStage);
        backgroundImage.loadTexture("magneticBG.png");
        backgroundImage.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        titleLogo = new PlayerActor(0,0,mainStage);
        titleLogo.loadTexture("gameOverTitle.png");
        titleLogo.setOrigin(titleLogo.getWidth()/2,titleLogo.getHeight()/2);
        titleLogo.setPosition(Gdx.graphics.getWidth()/2 - titleLogo.getWidth()/2,Gdx.graphics.getHeight()/2 + 100);
        titleLogo.setScale(3.0f);

        clickSound = Gdx.audio.newSound(Gdx.files.internal("clickSound.mp3"));
        clickSound.setVolume(0, EngineClass.GetInstance().MASTERSOUNDLEVEL);

        MainMenuButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                clickSound.play();
                EngineClass.GetInstance().LoadScene(new MainMenuScreen());
                return false;
            }
        });

        UITable.add(titleLogo);
        UITable.row();
        UITable.row();
        UITable.row();
        UITable.add(HighScoreText);
        UITable.row();
        UITable.add(MainMenuButton).width(Value.percentWidth(0.2f,UITable)).height(Value.percentHeight(0.1f,UITable));

        mainStage.addActor(UITable);
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
