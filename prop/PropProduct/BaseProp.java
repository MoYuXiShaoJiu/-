package com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.HeroAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.basic.AbstractFlyingObject;

public abstract class BaseProp extends AbstractFlyingObject
{

    public BaseProp(int locationX, int locationY, int speedX, int speedY)
    {
        super(locationX,locationY,speedX,speedY);
    }

    public int PropIndex(){
        return 0;
    }

    public void function1(AbstractAircraft Aircraft) {}

    public int BombAction(){
        return 0;
    };
}

