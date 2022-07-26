package com.example.aircraftwar.JavaCode.edu.hitsz.prop.propfactory;

import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.BaseProp;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.FireProp;


/**弹药道具工厂**/
public class FireFactory implements  PropFactory{
    @Override
    public BaseProp createProp(int locationX, int locationY, int speedX, int speedY){
        return new FireProp( locationX,  locationY,  speedX,  speedY);
    }
}
