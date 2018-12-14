package com.gravityflip.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.gravityflip.BaseScreen;
import com.gravityflip.Enemies.EnviromentBlockSpawner;
import com.gravityflip.Enemies.EnvironmentBlock;
import com.gravityflip.Enemies.MissileActor;
import com.gravityflip.PlayerActor;
import com.gravityflip.PlayerClass;

public class GameScene extends BaseScreen {

    final Skin SceneSkin =  new Skin(Gdx.files.internal("default/skin/uiskin.json"));
    //final Skin CustomSkin =  new Skin(Gdx.files.internal("default/DIEGO/Pause.json"));
    Table UITable;

    TextButton PauseButton;

    Label playerScoreText = new Label("0", SceneSkin);

    Texture BackgroundImage = new Texture(Gdx.files.internal("Background.jpg"));

    Texture BrickTexture = new Texture(Gdx.files.internal("platformIndustrial.png"));

    EnviromentBlockSpawner enviromentBlockSpawner;

    EnvironmentBlock playerActor;
    int counter = 0;

    boolean GamePaused = true;
    int playerScore = 0;
    float timeElapsed = 0;
    float timeToPoint = 1;
    int scoreIncrement = 2;

    PlayerClass player;
    boolean isPositive;

    MissileActor missileActor;
    Music gameMusic;

    public GameScene() {
        super();
        UITable = new Table();
        UITable.setDebug(false);
        UITable.columnDefaults(0).expandX();
        UITable.columnDefaults(1).expand();
        UITable.setFillParent(true);

        enviromentBlockSpawner = new EnviromentBlockSpawner("platformIndustrial.png", mainStage);

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("gameMusic.mp3"));
        gameMusic.setVolume( EngineClass.GetInstance().MASTERSOUNDLEVEL);
        gameMusic.setLooping(true);
        gameMusic.setPosition(4.0f);
        gameMusic.play();

        PauseButton = new TextButton("Options", SceneSkin);
        PauseButton.getLabel().setFontScale(3);
        PauseButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
               GamePaused = !GamePaused;
               Gdx.app.log("PAUSE GAME", "GAME PAUSED");

                return false;
            }
        });

        playerScoreText.setFontScale(4);
        ControllerTable.add(PauseButton).left().padBottom(100).padRight(SCREENWIDTH/2 - 200);
        ControllerTable.add(playerScoreText).right().padBottom(100).padLeft(SCREENWIDTH/2 - 200);

       player = new PlayerClass();
       player.setScale(2.0f);
       player.setOrigin(player.getX()/2,player.getY()/2);
       player.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
       //player.setWorldBounds(mainStage.getWidth(),mainStage.getHeight());
       //player.boundToWorld();
       player.setAcceleration(500);

        missileActor = new MissileActor();
        missileActor.setScale(0.1f);
        missileActor.setPosition(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/2);

        isPositive = true;

        mainStage.addActor(ControllerTable);

        mainStage.addActor(enviromentBlockSpawner);
        mainStage.addActor(player);
        mainStage.addActor(missileActor);
    }

    @Override
    public void Initialize() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        player.gravityAngle *= -1;
        if(isPositive) {
            isPositive = false;
            player.setAnimation("blueAnimString");
            // playerActor.setScale(3.0f);
        }
        else {
            isPositive = true;
            player.setAnimation("redAnimString");
            //  playerActor.setScale(3.0f);
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public void Update(float dt) {

        if (GamePaused) {
            playerScoreText.setText("" + playerScore);
            mainStage.act(dt);
            timeElapsed += dt;
            if (timeElapsed >= timeToPoint){
                timeElapsed = 0;
                playerScore += scoreIncrement;
                EngineClass.GetInstance().CURRENTSCORE = playerScore;
            }

            for (Actor actors : mainStage.getActors()) {
                if (actors.getX() < 0 - actors.getWidth()) {
                    actors.remove();
                }
            }
            if (enviromentBlockSpawner.activeBlocks != null) {
                for (EnvironmentBlock block : enviromentBlockSpawner.activeBlocks) {
                    if (block != null) {
                        if(block.BlockType == EnvironmentBlock.Type.Enviroment) {
                            if (player.overlaps(block)) {
                                player.preventOverlap(block);
                            }
                        }
                        if(block.BlockType == EnvironmentBlock.Type.Missile){
                            if(player.overlaps(block)){
                                block.remove();
                                gameMusic.stop();
                                EngineClass.GetInstance().LoadScene(new GameOverScreen());
                            }
                        }
                        if(block.BlockType == EnvironmentBlock.Type.EnviromentHazard){
                            if(player.overlaps(block)){
                                block.remove();
                                gameMusic.stop();
                                EngineClass.GetInstance().LoadScene(new GameOverScreen());
                            }
                        }
                    }
                }
            }
            if (player.getY() > Gdx.graphics.getHeight() + player.getHeight()) {
                Gdx.app.log("DEAD", "OUTSIDE TOP");
                gameMusic.stop();
                EngineClass.GetInstance().LoadScene(new GameOverScreen());
            }
            if (player.getY() < 0 - player.getHeight()) {
                Gdx.app.log("DEAD", "OUTSIDE BOTTOM");
                gameMusic.stop();
                EngineClass.GetInstance().LoadScene(new GameOverScreen());
            }
            if (player.getX() < 0 - player.getWidth()) {
                Gdx.app.log("DEAD", "OUTSIDE LEFT");
                gameMusic.stop();
                EngineClass.GetInstance().LoadScene(new GameOverScreen());
            }
            if (player.getX() > Gdx.graphics.getWidth() + player.getWidth()) {
                Gdx.app.log("DEAD", "OUTSIDE LEFT");
                gameMusic.stop();
                EngineClass.GetInstance().LoadScene(new GameOverScreen());
            }
        }
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

    @Override
    public void dispose() {
        super.dispose();
        gameMusic.dispose();
    }
}
