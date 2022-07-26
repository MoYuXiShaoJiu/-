package com.example.aircraftwar.JavaCode.edu.hitsz.aircraft;

import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.BaseBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {



    /** 攻击方式 */
    private int shootNum;     //子弹一次发射数量
    private int power = 30;       //子弹伤害
    private int direction = -1;  //子弹射击方向 (向上发射：1，向下发射：-1)

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */

    public int EnemyIndex = 1;

    private static HeroAircraft instance=null;



//以下为实验二通过单例实现英雄机的重构代码：：：：：：
    /**  创造实例只能通过class中的getInstance()进行，所以为private  **/
    private HeroAircraft (int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }


    /**定义获得实例的唯一方法getInstance()**/
    public static HeroAircraft getInstance(int locationX, int locationY, int speedX, int speedY, int hp)
    {
        if(instance ==null)
        {
            synchronized (HeroAircraft.class) {
                if(instance == null){
                    instance = new HeroAircraft(locationX, locationY, speedX, speedY, hp);
                }
            }
        }
        return instance;
    }
//以上为实验二通过单例实现英雄机的重构代码。




    @Override
    public int getAcIndex() {
        return 1;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 1;
        int speedY = this.getSpeedY() + direction*5;
        BaseBullet abstractBullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            abstractBullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX * (i*2 - shootNum + 1), speedY, power);
            res.add(abstractBullet);
        }
        return res;
    }

    @Override
    public void changeShootNum(int i){
         shootNum=i;
    }

    public void addShootNum(){
        if(shootNum<=10)
            shootNum+=1;
    }


}
