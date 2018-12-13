package com.gravityflip.Enemies;

public class EnvironmentHalfSaw extends EnvironmentBlock {
    final String[] SawSpin = {"platformHalfSaw01.png","platformHalfSaw02.png"};

    EnvironmentHalfSaw(){
        this.loadAnimationFromFiles("SawSpin",SawSpin, (1/6f), true);
        setAnimation("SawSpin");
        BlockType = Type.EnviromentHazard;
    }

}
