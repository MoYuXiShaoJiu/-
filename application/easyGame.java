package com.example.aircraftwar.JavaCode.edu.hitsz.application;

import android.content.Context;
import android.os.Handler;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.BaseEnemy;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.HeroAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.BaseBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.BaseProp;
import com.example.aircraftwar.JavaCode.edu.hitsz.strategy.BulletStrategyContext;
import com.example.aircraftwar.JavaCode.edu.hitsz.strategy.OperationBasicBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.application.Game;
import static com.example.aircraftwar.MainActivity.scHeight;
import static com.example.aircraftwar.MainActivity.scWidth;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class easyGame extends Game {

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
    Handler mHandler;

    public easyGame(Context context1) {
        super(context1);

        /**
         *
         *通过单例模式提供的唯一获取实例的方式：getInstance()来实例化唯一一个英雄机：：
         *
         * **/

        heroAircraft = HeroAircraft.getInstance(
                 scWidth/ 2,
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
       // new HeroController(this, heroAircraft);

    }
    @Override
    public void BossMaker() {}
    @Override
    public void gameParameterControl(){}


}
