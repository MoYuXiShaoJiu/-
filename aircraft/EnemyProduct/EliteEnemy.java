package com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
//import com.example.aircraftwar.JavaCode.edu.hitsz.application.Main;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.BaseBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.EnemyBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.observer.Subscriber;
import com.example.aircraftwar.JavaCode.edu.hitsz.strategy.BulletStrategyContext;
import com.example.aircraftwar.JavaCode.edu.hitsz.strategy.OperationBasicBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.strategy.Strategy;
import static com.example.aircraftwar.MainActivity.scHeight;
import static com.example.aircraftwar.MainActivity.scWidth;

import java.util.LinkedList;
import java.util.List;

public class EliteEnemy extends BaseEnemy implements Subscriber {

    private int shootNum;     //子弹一次发射数量
    private int power = 30;       //子弹伤害
    private int direction = -1;  //子弹射击方向 (向上发射：1，向下发射：-1)
    int speedX = 0;
    int speedY = this.getSpeedY() - direction*5;


    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public int getAcIndex() {
        return 3;
    }

    @Override
    public void forward() {
        super.forward();
        if (locationY >= scHeight ) {
            vanish();
        }
    }

    @Override
    public void changeShootNum(int i){
        shootNum = i;
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() ;
        shootNum = 1;
        BaseBullet abstractBullet;

            abstractBullet = new EnemyBullet(x , y+50, 0, speedY, power);
            res.add(abstractBullet);

        return res;

    }

    @Override
    public void update(){
        vanish();
    }


}
