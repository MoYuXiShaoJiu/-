package com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.application.ImageManager;
//import com.example.aircraftwar.JavaCode.edu.hitsz.application.Main;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.BaseBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.EnemyBullet;
import static com.example.aircraftwar.MainActivity.scHeight;
import static com.example.aircraftwar.MainActivity.scWidth;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends BaseEnemy {

    private int shootNum;     //子弹一次发射数量
    private int power = 30;       //子弹伤害
    private int direction = -1;  //子弹射击方向 (向上发射：1，向下发射：-1)
    int speedY = this.getSpeedY() - direction*5;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public int getAcIndex() {
        return 4;
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
        shootNum=i;
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() ;
        BaseBullet abstractBullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            abstractBullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, this.speedX * (i*2 - shootNum + 1)/2, speedY, power);
            res.add(abstractBullet);
        }
        return res;

    }


}
