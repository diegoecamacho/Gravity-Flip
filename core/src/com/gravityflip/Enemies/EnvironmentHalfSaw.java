package com.gravityflip.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gravityflip.ActorBase;

public class EnvironmentHalfSaw extends EnvironmentBlock {
    final String[] SawSpin = {"platformHalfSaw01.png","platformHalfSaw02.png"};
    public Animation<TextureRegion> sawAnim;

    EnvironmentHalfSaw(){
        sawAnim = this.loadAnimationFromFiles(SawSpin, (1/6f), true);
        setAnimation(sawAnim);
        BlockType = Type.Damage;
    }

}
