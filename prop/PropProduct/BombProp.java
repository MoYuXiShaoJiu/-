package com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.BaseEnemy;
//import com.example.aircraftwar.JavaCode.edu.hitsz.application.Main;
import static com.example.aircraftwar.MainActivity.scHeight;
import static com.example.aircraftwar.MainActivity.scWidth;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.BaseBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.EnemyBullet;

import java.util.ArrayList;
import java.util.List;

public class BombProp extends BaseProp {
    public BombProp(int locationX, int locationY, int speedX, int speedY) {
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
        return 3;
    }

    public int scoreIncrease = 0;




    /**以下为实验六需要实现的观察者模式中的发布者部分**/
    private List<BaseEnemy> EnemyList = new ArrayList<>();
    private List<BaseBullet> BulletList = new ArrayList<>();

    public void addSubscriber(List<BaseEnemy> enemyList, List<BaseBullet> bulletList){
        EnemyList = enemyList;
        BulletList = bulletList;
    }
    public void removeSubscriber(){
        for(BaseEnemy i:EnemyList){
            EnemyList.remove(i);

        }
        for(BaseBullet j:BulletList){
            BulletList.remove(j);
        }
    }



    public void notifyAllSub(){
        scoreIncrease=0;
        for(BaseEnemy i:EnemyList){
            i.update();
            if(i.getAcIndex()==2){
                scoreIncrease+=30;
            }
            else{
                scoreIncrease+=50;
            }
        }
        for(BaseBullet j:BulletList){
            ((EnemyBullet)j).update();
        }
    }

    @Override
    public int BombAction(){
        notifyAllSub();
        return scoreIncrease;
    }


}
