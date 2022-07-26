package com.example.aircraftwar.JavaCode.edu.hitsz.application;

import static com.example.aircraftwar.JavaCode.edu.hitsz.application.Main.soundSwitch;

public class MusicManager {
    private static boolean enable = false;
    private static final String[] music = new String[10];
    static {music[0]="src/videos/bgm.wav";music[1]="src/videos/bgm_boss.wav";music[2]="src/videos/bomb_explosion.wav";music[3]="src/videos/bullet.wav";music[4]="src/videos/bullet_hit.wav";music[5]="src/videos/game_over.wav";music[6]="src/videos/get_supply.wav";}
    private static MusicThread bgm ;
    private static MusicThread boss ;

    public static void Enable(boolean Enable){
        enable = Enable;
    }
    public static void bgmBegin() throws InterruptedException {
        if(enable && soundSwitch){
            bgm=new MusicThread(music[0], true);
            bgm.start();
        }

    }
    public static void Boss() throws InterruptedException {
        if (enable) {
            boss = new MusicThread(music[1], true);
            boss.start();
        }

    }
    public static void BossDied() {
        if(enable){
            boss.letEnd();
        }

    }
    public static void bombBlow(){
        if(enable){
            new MusicThread(music[2],false ).start();
        }

    }
    public static void bulletHit(){
        if(enable){
            new MusicThread(music[4],false ).start();
        }

    }
    public static void gameOver() throws InterruptedException {
        if(enable){
            if(bgm != null)
                bgm.letEnd();
            if(boss!=null)
                boss.letEnd();
            new MusicThread(music[5],false ).start();

        }

    }
    public static void propGet(){
        if(enable){
            new MusicThread(music[6],false ).start();
        }

    }

}

