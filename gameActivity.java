package com.example.aircraftwar;

import static com.example.aircraftwar.MainActivity.musicSwitch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar.JavaCode.edu.hitsz.application.MusicService;
import com.example.aircraftwar.JavaCode.edu.hitsz.application.hardGame;
import com.example.aircraftwar.JavaCode.edu.hitsz.application.normalGame;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.aircraftwar.JavaCode.edu.hitsz.application.easyGame;



public class gameActivity extends AppCompatActivity {
    public static Handler mh;
    public static MusicService.MyBinder myBinder;
    private Connect conn;
    private Intent intent;
    private String TAG = "gameActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent rank=new Intent(this,RankActivity.class);
     //   setContentView(R.layout.activity_game_avtivity);

        setContentView(R.layout.activity_game_avtivity);

        conn = new Connect();
        intent = new Intent(this, MusicService.class);
        Log.i(TAG,"gameActicity onCreate");

        bindService(intent,conn, Context.BIND_AUTO_CREATE);


        mh=new Handler(getMainLooper()){
            @Override
            public void handleMessage(Message msg)
            {
                if(msg.what==1)
                {
                    startActivity(rank);
                }
            }
        };





    }


    public void  easyModel (View view)
    {

        MainActivity.diffculity=1;
        easyGame EG = new easyGame(this);
        setContentView(EG);
        EG.action();


    }
    public  void normalModel(View view)
    {

        MainActivity.diffculity=2;
        normalGame NG=new normalGame(this);
        setContentView(NG);
        NG.action();

    }
    public  void hardModel(View view)
    {
        MainActivity.diffculity=3;
        hardGame HG=new hardGame(this);
        setContentView(HG);
        HG.action();

    }
    public  void musicControl(View view)
    {
        if(musicSwitch==false)
        {
            musicSwitch=true;
        }
        else if(musicSwitch=true)
        {
            musicSwitch=false;
        }

    }



    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("musicdemo","stop");
        unbindService(conn);
    }

    class Connect implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service){
            Log.i("musicdemo","Service Connnected");
            myBinder = (MusicService.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {}
    }



}