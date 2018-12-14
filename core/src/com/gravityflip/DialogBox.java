package com.gravityflip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;


public class DialogBox extends PlayerActor {

    private Label dialogLabel;
    private Label.LabelStyle labelStyle;
    private float padding = 16;

    private Skin skin;

    DialogBox(float x, float y, Stage s) {
        super(x, y, s);
        //loadTexture("UI/dialog-translucent.png");

        skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));

        dialogLabel = new Label(" ", skin, "default");
        dialogLabel.setWrap(true);
        dialogLabel.setPosition(x * padding, y * padding);
        this.setDialogSize(getWidth(), getHeight());

        this.setDebug(true);

        s.addActor(dialogLabel);
    }

    public void setDialogSize(float width, float height) {
        this.setSize(width, height);
        dialogLabel.setWidth(width - 2 * padding);
        dialogLabel.setHeight(height - 2 * padding);
    }

    public void setText(String text) {
        dialogLabel.setText(text);
    }

    public void setFontScale(float scale) {
        dialogLabel.setFontScale(scale);
    }

    public void setFontColor(Color color) {
        dialogLabel.setColor(color);
    }

    public void setBackgroundColor(Color color) {
        this.setColor(color);
    }

    public void alignTopLeft() {
        dialogLabel.setAlignment(Align.topLeft);
    }

    public void alignCenter() {
        dialogLabel.setAlignment(Align.center);
    }

}