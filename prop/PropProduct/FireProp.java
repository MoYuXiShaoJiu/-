package com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.HeroAircraft;
import static com.example.aircraftwar.MainActivity.scHeight;
import static com.example.aircraftwar.MainActivity.scWidth;

public class FireProp extends BaseProp {

    public FireProp(int locationX, int locationY, int speedX, int speedY) {
        super( locationX, locationY, speedX, speedY );
    }

    @Override
    public void forward(){
        super.forward();
        if (speedY > 0 && locationY >= scHeight ) {
            // 向下飞行出界
            vanish();
        }
        else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }

    @Override
    public int PropIndex(){
        return 2;
    }


    public void function1(HeroAircraft Aircraft){
        Aircraft.changeShootNum(2);
    }
}
