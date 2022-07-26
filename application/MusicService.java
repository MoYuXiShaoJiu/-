package com.example.aircraftwar.JavaCode.edu.hitsz.application;

import static com.example.aircraftwar.MainActivity.musicSwitch;
import static com.example.aircraftwar.gameActivity.myBinder;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.aircraftwar.R;

import java.util.HashMap;

public class MusicService extends Service {
    private static  final String TAG = "MusicService";
    private HashMap<Integer, Integer> soundID = new HashMap<Integer, Integer>();
    private SoundPool mSoundPool;
    private MediaPlayer player_bgm;
    private MediaPlayer player_bgm_boss;
    private Context context1 = this;

    public MusicService(){}

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "==== MusicService onCreate ===");
        mSoundPool = new SoundPool(6, AudioManager.STREAM_SYSTEM, 5);
        soundID.put(1, mSoundPool.load(this, R.raw.bullet_hit, 1));
        //soundID.put(,mSoundPool.load(this,R.raw.bgm_boss,1));
        soundID.put(2, mSoundPool.load(this, R.raw.bullet, 1));
        soundID.put(3, mSoundPool.load(this, R.raw.bomb_explosion, 1));
        soundID.put(4, mSoundPool.load(this, R.raw.get_supply, 1));
        soundID.put(5, mSoundPool.load(this, R.raw.game_over, 1));


    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {

        public void playMusic(int id) {
            Log.i(TAG,"musicSwitch:"+musicSwitch);
            if (musicSwitch) {
                mSoundPool.play(soundID.get(id), 1, 1, 0, 0, 1);
            }
        }

        public void playBGM(int id){

            if(musicSwitch) {
                if(id==1){
                    if (player_bgm == null) {
                        player_bgm = MediaPlayer.create(context1, R.raw.bgm);
                        player_bgm.setLooping(true);
                    }
                    Log.i("BGM","111111111111");
                    player_bgm.start();
                }
                else if(id==2){
                    if (player_bgm_boss == null) {
                        player_bgm_boss = MediaPlayer.create(context1, R.raw.bgm_boss);
                        player_bgm_boss.setLooping(true);
                    }
                    player_bgm_boss.start();
                }
            }
        }

        public void stopMusic(int id) {
            if(id==1){
                if (player_bgm != null) {
                    player_bgm.stop();
                    player_bgm.reset();//重置
                    player_bgm.release();//释放
                    player_bgm = null;
                }
            }
            else if(id==2){
                if (player_bgm_boss != null) {
                    player_bgm_boss.stop();
                    player_bgm_boss.reset();//重置
                    player_bgm_boss.release();//释放
                    player_bgm_boss = null;
                }
            }
        }

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        myBinder.stopMusic(1);
        myBinder.stopMusic(2);
    }
    /**
     * 停止播放
     */


}
