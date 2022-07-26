package com.example.aircraftwar.JavaCode.edu.hitsz.application;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.aircraftwar.R;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.BossEnemy;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.HeroAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.MobEnemy;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.EliteEnemy;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.EnemyBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.HeroBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.FireProp;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.HpProp;
import com.example.aircraftwar.JavaCode.edu.hitsz.prop.PropProduct.BombProp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, Bitmap> CLASSNAME_IMAGE_MAP = new HashMap<>();


    public static Bitmap BACKGROUND_IMAGE_easy;
    public static Bitmap BACKGROUND_IMAGE_normal;
    public static Bitmap BACKGROUND_IMAGE_hard;

    public static Bitmap HERO_IMAGE;
    public static Bitmap MOB_ENEMY_IMAGE;
    public static Bitmap ELITE_ENEMY_IMAGE;
    public static Bitmap BOSS_ENEMY_IMAGE;

    public static Bitmap HERO_BULLET_IMAGE;
    public static Bitmap ENEMY_BULLET_IMAGE;



    public static Bitmap HP_PROP_IMAGE;
    public static Bitmap FIRE_PROP_IMAGE;
    public static Bitmap BOMB_PROP_IMAGE;




    public static void initImage(Context context){
        BACKGROUND_IMAGE_easy = BitmapFactory.decodeResource(context.getResources(),R.drawable.bg);
        BACKGROUND_IMAGE_normal = BitmapFactory.decodeResource(context.getResources(),R.drawable.bg2);
        BACKGROUND_IMAGE_hard = BitmapFactory.decodeResource(context.getResources(),R.drawable.bg3);
        HERO_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.hero);
        MOB_ENEMY_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.mob);
        ELITE_ENEMY_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.elite);
        BOSS_ENEMY_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.boss);
        HERO_BULLET_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.bullet_hero);
        ENEMY_BULLET_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.bullet_enemy);
        HP_PROP_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.prop_blood);
        FIRE_PROP_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.prop_bullet);
        BOMB_PROP_IMAGE = BitmapFactory.decodeResource(context.getResources(),R.drawable.prop_bomb);


        CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
        CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
        CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
        CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(), BOSS_ENEMY_IMAGE);

        CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
        CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);

        CLASSNAME_IMAGE_MAP.put(HpProp.class.getName(), HP_PROP_IMAGE);
        CLASSNAME_IMAGE_MAP.put(FireProp.class.getName(), FIRE_PROP_IMAGE);
        CLASSNAME_IMAGE_MAP.put(BombProp.class.getName(), BOMB_PROP_IMAGE);

    }

    public static Bitmap get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static Bitmap get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }

}
