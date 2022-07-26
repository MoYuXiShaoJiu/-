package com.example.aircraftwar.JavaCode.edu.hitsz.application;

import com.example.aircraftwar.JavaCode.edu.hitsz.HeroController;
import com.example.aircraftwar.JavaCode.edu.hitsz.RecordDao.Record;
import com.example.aircraftwar.JavaCode.edu.hitsz.RecordDao.RecordDao;
import com.example.aircraftwar.JavaCode.edu.hitsz.RecordDao.RecordDaoImpl;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.*;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyFactory.BossEnemyFactory;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyFactory.EliteEnemyFactory;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyFactory.MobEnemyFactory;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.BaseEnemy;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.BaseBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.basic.AbstractFlyingObject;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.EnemyBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.BombProp;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.propfactory.BombFactory;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.propfactory.FireFactory;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.propfactory.HpFactory;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.BaseProp;
import com.example.aircraftwar.JavaCode.edu.hitsz.strategy.BulletStrategyContext;
import com.example.aircraftwar.JavaCode.edu.hitsz.strategy.OperationBasicBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.strategy.OperationScatterBullet;
import com.example.aircraftwar.gameActivity;

//import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

//import static com.example.aircraftwar.JavaCode.edu.hitsz.application.Main.*;
import static com.example.aircraftwar.MainActivity.diffculity;
import static com.example.aircraftwar.MainActivity.scHeight;
import static com.example.aircraftwar.MainActivity.scWidth;
import static com.example.aircraftwar.gameActivity.mh;
import static com.example.aircraftwar.gameActivity.myBinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract   class Game extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    boolean mbLoop = false;
    private SurfaceHolder mSurfaceHolder;
    private Canvas canvas;
    private Paint mPaint;

    private int backGroundTop = 0;
    int heroBulletDamage=0;
    double bossScoreThreshold = 600;
    double bossScoreThreshold_decrease=0;
    double bossHpIncrease = 0;
    private double enemyHpIncrease = 0;
    private double enemySpeedIncrease = 0;
    private double bossHpin = 0;

    double bossHpRatio = 1;
    double enemyHpRatio = 1;
    double enemySpeedRatio = 1;
    public Handler thisH = mh;


    /**
     * Scheduled 线程池，用于任务调度
     */
    //private final ScheduledExecutorService executorService;

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

    int enemyMaxNumber = 5;


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
    private int time1=0;
    private int score_1000=0;
    private int score_0 = 0;
    public void setMH(Handler h)
    {
        this.thisH=h;
    }

    public Game(Context context1) {

        super(context1);
        mbLoop = true;
        mPaint = new Paint();
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        this.setFocusable(true);

        ImageManager.initImage(context1);
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
        HeroController HC=new HeroController(this,heroAircraft);

        ImageManager.initImage(context1);


        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        Props = new LinkedList<>();

        //Scheduled 线程池，用于定时任务调度
       // executorService = new ScheduledThreadPoolExecutor(1);

        //启动英雄机鼠标监听

      /**  new HeroController(this, heroAircraft); **/
    }




    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
      //  Runnable task = () -> {

       //     time += timeInterval;
            myBinder.playBGM(1);


            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);

                //根据游戏难度，时间进行参数更改：
                //每5s更改一次参数
                if(time - time1>=5000) {
                    gameParameterControl();
                    time1 = time;
                }

                // 新敌机产生

                /**创建敌机时，需要调用非static方法createEnemy，
                 * 所以不能直接调用，需要先实例化每一种敌机，
                 * 之后通过实例进行函数调用**/
                if (enemyAircrafts.size() < enemyMaxNumber) {

                    score_1000=score-score_0;

                    double random = Math.random();
                    if (score_1000 >= bossScoreThreshold)
                    {
                        BossMaker();
                    }

                    if( random <0.6 ) {

                        MobMaker();

                    }

                    else{
                        EliteMaker();
                    }
                }

                // 飞机射出子弹
                shootAction();

            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //道具移动
            propsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();


            //每个时刻重绘界面 repaint
            draw();

            //子弹道具时间管理：
            if(time-time0>=3000)
            {
                context.setStrategy(new OperationBasicBullet());
                context.executeStrategyHero(heroAircraft);
            }


            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                mbLoop = false;
                /**储存记录，代码现在已经转移至panel—scoreTable**/
/*
                RecordDaoImpl recordDao = new RecordDaoImpl();

                recordDao.readRecords();



                Record recordNew = new Record("username",score);
                LocalDate localDate = LocalDate.now();
                LocalTime localTime = LocalTime.now();
                String time1 = String.valueOf(localDate);
                time1 = time1 +" "+ localTime;
                recordNew.setTime(time1);
                recordDao.doAdd(recordNew);
                recordDao.RecordsSort();
                recordDao.deleteAtMax();
                recordDao.writeRecords();
                recordDao.printAllRecords();
*/



                // 游戏结束
/*
                FinalScore = score;
                executorService.shutdown();

                synchronized (MAIN_LOCK){
                    MAIN_LOCK.notify();
                }*/

                myBinder.playMusic(5);

                myBinder.stopMusic(1);

                gameOverFlag = true;
                Message msg=new Message();
                msg.what=1;
                thisH.sendMessage(msg);


                System.out.println("Game Over!");
            }



     //   };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
       // executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    public void BossMaker(){
        BossEnemyFactory bossEnemyFactory = new BossEnemyFactory();
/**
        try {
            MusicManager.Boss();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
**/
        myBinder.playBGM(2);
        enemyAircrafts.add((BaseEnemy) bossEnemyFactory.createEnemy(
                (scWidth/2)*1,
                scHeight-1500,
                2,
                0,
                (int) (300*bossHpRatio)
        ));
        bossHpRatio += bossHpIncrease;
        if(bossScoreThreshold-bossScoreThreshold_decrease>=200){
            bossScoreThreshold-=bossScoreThreshold_decrease;
        }
        score_0=10000000;
    }

    public void MobMaker(){
        MobEnemyFactory mobEnemyFactory = new MobEnemyFactory();

        BaseEnemy mobEnemyNew = (BaseEnemy) mobEnemyFactory.createEnemy(
                (int) (Math.random() * (scWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * scHeight * 0.2) * 1,
                0,
                (int) (8*enemySpeedRatio),
                (int) (30*enemyHpRatio)
        );
        enemyAircrafts.add(mobEnemyNew);
    }

    public void EliteMaker(){
        int i=0;
        if(Math.random()<0.5)
        {
            i=1;
        }
        else
        {
            i=-1;
        }
        EliteEnemyFactory eliteEnemyFactory = new EliteEnemyFactory();
        BaseEnemy eliteEnemyNew = (BaseEnemy) eliteEnemyFactory.createEnemy(
                (int) (Math.random() * (scWidth - ImageManager.ELITE_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * scHeight * 0.2) * 1,
                (int) (3*i*enemySpeedRatio),
                (int) (8*enemySpeedRatio),
                (int) (30*enemyHpRatio));

        enemyAircrafts.add(eliteEnemyNew);
    }

    public void gameParameterControl(){
        //设置每次召唤boss所减小的分数阈值,最小值为200分
        bossScoreThreshold_decrease = 0;

        //设置每次召唤boss所增加的敌机血量
        bossHpIncrease = 0;
        //设置敌机的属性值
        enemyHpRatio+=0;
        enemySpeedRatio+=0;
        //设置敌机数量的最大值
        enemyMaxNumber+=0;
        //设置英雄机弹药的伤害
        heroBulletDamage-=0;
        System.out.println("游戏难度增加，"+"boss产生的分数阈值变为："+bossScoreThreshold+",boss血量比例为："+bossHpRatio+",普通和精英机血量比例为："+enemyHpRatio+",普通和精英机速度比例为："+enemySpeedRatio+",敌机最大数量为："+enemyMaxNumber+",英雄机子弹伤害削减为:"+heroBulletDamage);
    }


    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击
        for(AbstractAircraft enemyAircraft : enemyAircrafts) {
            context.executeStrategyElite(enemyAircraft);
            enemyBullets.addAll( enemyAircraft.shoot() );
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
        myBinder.playMusic(2);
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void propsMoveAction() {
        for (BaseProp Prop : Props) {
            Prop.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄

        for (BaseBullet bullet: enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
//
            if (heroAircraft.notValid()) {
                // 已被其他子弹击毁的敌机，不再检测
                // 避免多个子弹重复击毁同一敌机的判定
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                // 敌机撞击到英雄机子弹
                // 敌机损失一定生命值
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
//
        }


        /** 英雄子弹攻击敌机 **/
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }



            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定

                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    /**MusicManager.bulletHit();*/
                    myBinder.playMusic(1);

                    if (enemyAircraft.notValid()) {

                        // TODO 获得分数，产生道具补给

                        if(enemyAircraft.getAcIndex()==2) {
                            score += 30;

                        }
                        else if(enemyAircraft.getAcIndex()==3){
                            score +=50;  //精英敌人加50分


                            /**创建道具时，需要调用非static方法createProp，
                             * 所以不能直接调用，需要先实例化每一种道具，
                             * 之后通过实例进行函数调用**/

                            if( Math.random() < 0.9 ) {
                                double random = Math.random();
                                if (random < 0.33) {  //产生加血道具

                                    HpFactory hpFactory = new HpFactory();

                                    Props.add(hpFactory.createProp(
                                            enemyAircraft.getLocationX()*1,
                                            enemyAircraft.getLocationY()*1,
                                            0,
                                            10
                                    ));
                                }
                                //产生弹药道具
                                else if (  random >0.33 && random < 0.66) {

                                    FireFactory fireFactory = new FireFactory();

                                    Props.add(fireFactory.createProp(
                                            enemyAircraft.getLocationX()*1,
                                            enemyAircraft.getLocationY()*1,
                                            0,
                                            10
                                    ));
                                }
                                //产生炸弹道具
                                else if ( random > 0.66 ) {

                                    BombFactory bombFactory = new BombFactory();

                                    Props.add(bombFactory.createProp(
                                            enemyAircraft.getLocationX()*1,
                                            enemyAircraft.getLocationY()*1,
                                            0,
                                            10
                                    ));
                                }




                            }


                        }
                        else if(enemyAircraft.getAcIndex()==4){
                                double random = Math.random();
                            if (random < 0.33) {  //产生加血道具

                                HpFactory hpFactory = new HpFactory();

                                Props.add(hpFactory.createProp(
                                        enemyAircraft.getLocationX()*1,
                                        enemyAircraft.getLocationY()*1,
                                        0,
                                        10
                                ));
                            }
                            //产生弹药道具
                            else if (  random >0.33 && random <=0.66) {

                                FireFactory fireFactory = new FireFactory();

                                Props.add(fireFactory.createProp(
                                        enemyAircraft.getLocationX()*1,
                                        enemyAircraft.getLocationY()*1,
                                        0,
                                        10
                                ));
                            }
                            //产生炸弹道具
                            else if ( random > 0.66 ) {

                                BombFactory bombFactory = new BombFactory();

                                Props.add(bombFactory.createProp(
                                        enemyAircraft.getLocationX()*1,
                                        enemyAircraft.getLocationY()*1,
                                        0,
                                        10
                                ));
                            }


                            score+=500;
                            score_0 = score;
                           /** MusicManager.BossDied();*/
                            myBinder.stopMusic(2);
                        }
                    }
                }

                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                    heroAircraft.vanish();
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for(BaseProp Prop: Props){
            if(heroAircraft.crash(Prop)){
                /**MusicManager.propGet();*/
                myBinder.playMusic(4);


                if(Prop.PropIndex()==1){
                    Prop.function1(heroAircraft);   //捡到加血道具加20滴血
                    Prop.vanish();
                }
                else if(Prop.PropIndex()==2){       //捡到弹药道具了，打印相关文字
                    time0 = time;
                    context.setStrategy(new OperationScatterBullet());
                    context.executeStrategyHero(heroAircraft);
                    Prop.vanish();
                    System.out.println("FireSupply active!");

                }

                else if(Prop.PropIndex()==3){     //捡到炸弹道具，打印相关文字

                    /*
                    for  (AbstractAircraft i : enemyAircrafts)
                    {
                        if(i.getAcIndex()!=4)
                        {
                            Prop.function1(i);
                            if(i.getAcIndex()==3){
                                score+=50;
                            }
                            else if(i.getAcIndex()==2){
                                score+=30;
                            }
                        }
                    }
                    for(BaseBullet enemybullet: enemyBullets){
                        enemybullet.vanish();
                    }
                    */

                    ((BombProp) Prop).addSubscriber(enemyAircrafts,enemyBullets);
                    score += ((BombProp) Prop).BombAction();
                    Prop.vanish();
                    /**MusicManager.bombBlow();*/
                    myBinder.playMusic(3);
                    System.out.println("BombSupply active!");
                }
            }



        }

    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        Props.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     */
  //  @Override
    public void draw() {

        canvas = mSurfaceHolder.lockCanvas();
        if(mSurfaceHolder == null || canvas == null){
            return;
        }


        // 绘制背景,图片滚动
        if(diffculity==1){
            canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE_easy, 0, this.backGroundTop - ImageManager.BACKGROUND_IMAGE_easy.getHeight(), null);
            canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE_easy, 0, this.backGroundTop,null);
        }
        else if(diffculity==2)
        {
            canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE_normal, 0, this.backGroundTop - ImageManager.BACKGROUND_IMAGE_normal.getHeight(), null);
            canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE_normal, 0, this.backGroundTop,null);
        }
        else if(diffculity==3)
        {
            canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE_hard, 0, this.backGroundTop - ImageManager.BACKGROUND_IMAGE_hard.getHeight(), null);
            canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE_hard, 0, this.backGroundTop,null);
        }
//        canvas.drawBitmap(BG, 0, this.backGroundTop - BG.getHeight(), null);
//        canvas.drawBitmap(BG, 0, this.backGroundTop,null);
        this.backGroundTop += 1;
        if (this.backGroundTop == scHeight) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制道具，最后绘制飞机
        // 这样子弹显示在道具下层，而道具显示在飞机的下层
        paintImageWithPositionRevised(canvas, enemyBullets);
        paintImageWithPositionRevised(canvas, heroBullets);

        paintImageWithPositionRevised(canvas, Props);

        paintImageWithPositionRevised(canvas, enemyAircrafts);

        canvas.drawBitmap(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(canvas);

        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    private void paintImageWithPositionRevised(Canvas g, List<? extends AbstractFlyingObject> objects) {
        if (objects != null && objects.size() == 0) {
            return;
        }
        else {
            for (int i=0;i<objects.size();i++) {
                Bitmap image = objects.get(i).getImage();
                assert image != null : objects.getClass().getName() + " has no image! ";
                g.drawBitmap(image, objects.get(i).getLocationX() - image.getWidth() / 2,
                        objects.get(i).getLocationY() - image.getHeight() / 2, null);
            }
        }
    }

    private void paintScoreAndLife(Canvas g) {
        int x = 10;
        int y = 300;
      //  mPaint.setColor(16711680);
        mPaint.setTextSize(90);

      //  g.(new Font("SansSerif", Font.BOLD, 22));
        g.drawText("SCORE:" + this.score, x, y,mPaint);
        y = y + 80;
        g.drawText("LIFE:" + this.heroAircraft.getHp(), x, y,mPaint);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
        scHeight = height;
        scWidth = width;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        mbLoop = false;
    }

    @Override
    public void run() {
        while (mbLoop){
            synchronized (mSurfaceHolder){
                action();
                draw();
            }
            try {
           //     Thread.sleep(200);
            }catch (Exception e){}
        }
    }
}
