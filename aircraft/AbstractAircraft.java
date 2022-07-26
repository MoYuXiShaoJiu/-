package com.example.aircraftwar.JavaCode.edu.hitsz.aircraft;

import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.BaseBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.basic.AbstractFlyingObject;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;




    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public void increaseHp(int increase){
        if(hp + increase > maxHp)
        {
            hp = maxHp;
        }
        else{
            hp = hp+increase;
        }
    }

    public int getHp() {
        return hp;
    }



    /**     获得飞行器编号，以区分不同飞行器种类：
     *      编号为1：   英雄机
     *      编号为2：   普通敌机
     *      编号为3：   精英敌机
     *      编号为4：   Boss敌机
     *      **/

    public int getAcIndex(){return 0;}


    /**
     * 飞机射击方法，可射击对象必须实现
     * @return
     *  可射击对象需实现，返回子弹
     *  非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();
    public abstract void changeShootNum(int i);

}


