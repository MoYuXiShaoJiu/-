package com.example.aircraftwar.JavaCode.edu.hitsz.application;

import android.content.Context;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.BaseEnemy;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.HeroAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.BaseBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.BaseProp;
import com.example.aircraftwar.JavaCode.edu.hitsz.strategy.BulletStrategyContext;
import com.example.aircraftwar.JavaCode.edu.hitsz.strategy.OperationBasicBullet;
import static com.example.aircraftwar.MainActivity.scHeight;
import static com.example.aircraftwar.MainActivity.scWidth;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class normalGame extends Game{




    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<BaseEnemy> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<BaseProp> Props;
    BulletStrategyContext context = new BulletStrategyContext(new OperationBasicBullet());

    private int enemyMaxNumber = 5;


    public boolean gameOverFlag = false;
    private int score = 0;
    private int time = 0;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;
    private int time0=1000000;
    private int score_1000=0;
    private int score_0 = 0;

    public normalGame(Context context1){
        super(context1);

        /**
         *
         *通过单例模式提供的唯一获取实例的方式：getInstance()来实例化唯一一个英雄机：：
         *
         * **/

        heroAircraft = HeroAircraft.getInstance(
        scWidth / 2,
        scHeight - ImageManager.HERO_IMAGE.getHeight() ,
                0, 0, 1000);
        context.executeStrategyHero(heroAircraft);




        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        Props = new LinkedList<>();

        //Scheduled 线程池，用于定时任务调度
        executorService = new ScheduledThreadPoolExecutor(1);

        //启动英雄机鼠标监听
      //  new HeroController(this, heroAircraft);

    }

    @Override
    public void gameParameterControl(){
        //设置每次召唤boss所减小的分数阈值,最小值为200分
        //boss血量不根据时间改变，而是根据召唤次数改变！
        super.bossScoreThreshold_decrease = 30;

        //设置每次召唤boss所增加的敌机血量
        super.bossHpIncrease = 0;
        //设置敌机的属性值
        super.enemyHpRatio+=0.1;
        super.enemySpeedRatio+=0.1;
        //设置敌机数量的最大值
        super.enemyMaxNumber+=1;
        //设置英雄机弹药的伤害
        super.heroBulletDamage-=3;
        System.out.println("游戏难度增加，"+"boss产生的分数阈值变为："+super.bossScoreThreshold+",boss血量比例为："+super.bossHpRatio+",普通和精英机血量比例为："+super.enemyHpRatio+",普通和精英机速度比例为："+super.enemySpeedRatio+",敌机最大数量为："+super.enemyMaxNumber+",英雄机子弹伤害削减为:"+super.heroBulletDamage);
        System.out.println("boss的血量和产生的分值阈值只和boss召唤次数有关，与时间无关");

    }


}
