package com.example.aircraftwar.JavaCode.edu.hitsz.prop.propfactory;

import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.BaseProp;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.BombProp;


/**炸弹道具工厂**/
/** @author tts **/
public class BombFactory implements  PropFactory{
    @Override
    public BaseProp createProp(int locationX, int locationY, int speedX, int speedY){
        return new BombProp( locationX,  locationY,  speedX,  speedY);
    }
}
