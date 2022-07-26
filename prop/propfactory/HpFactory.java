package com.example.aircraftwar.JavaCode.edu.hitsz.prop.propfactory;

import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.BaseProp;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.HpProp;

/**Hp道具工厂**/

public class HpFactory implements  PropFactory{
    @Override
    public BaseProp createProp(int locationX, int locationY, int speedX, int speedY){
        return new HpProp( locationX,  locationY,  speedX,  speedY);
    }
}
