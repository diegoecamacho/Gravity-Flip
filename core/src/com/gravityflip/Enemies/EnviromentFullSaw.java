package com.gravityflip.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gravityflip.ActorBase;

public class EnviromentFullSaw extends EnvironmentBlock {
    final String[] SawSpin = {"platformfullSaw01.png","platformfullSaw02.png"};
    public Animation<TextureRegion> sawAnim;

    EnviromentFullSaw(){
        sawAnim = this.loadAnimationFromFiles(SawSpin, (1/6f), true);
        setAnimation(sawAnim);
        BlockType = Type.Damage;
    }


}
