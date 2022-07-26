package com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
import static com.example.aircraftwar.MainActivity.scHeight;
import static com.example.aircraftwar.MainActivity.scWidth;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.BaseBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.observer.Subscriber;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends BaseEnemy implements Subscriber {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public int getAcIndex() {
        return 2;
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= scHeight ) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }

    @Override
    public void changeShootNum(int i){}

    @Override
    public void update(){
        vanish();
    }
}
