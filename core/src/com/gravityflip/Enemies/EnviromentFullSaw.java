package com.gravityflip.Enemies;

public class EnviromentFullSaw extends EnvironmentBlock {
    final String[] SawSpin = {"platformfullSaw01.png","platformfullSaw02.png"};


    EnviromentFullSaw(){
        this.loadAnimationFromFiles("SawSpin" , SawSpin, (1/6f), true);
        setAnimation("SawSpin");
        BlockType = Type.EnviromentHazard;
    }


}
