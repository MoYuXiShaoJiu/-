package com.example.aircraftwar.JavaCode.edu.hitsz.prop.propfactory;

import com.example.aircraftwar.JavaCode.edu.hitsz.basic.AbstractFlyingObject;

/**道具工厂接口**/
public interface PropFactory {
    AbstractFlyingObject createProp(int locationX, int locationY, int speedX, int speedY);
}
