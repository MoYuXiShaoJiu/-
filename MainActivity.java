package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.example.aircraftwar.JavaCode.edu.hitsz.application.ImageManager;


public class MainActivity extends AppCompatActivity {

    public static int diffculity;
    public static  boolean musicSwitch=false;
    public static int scWidth;
    public static int scHeight;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreenHW();
        setContentView(R.layout.activity_main);
    }
    // 获取窗口宽高
    public void getScreenHW(){
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        scWidth = dm.widthPixels;
   //     Log.i(TAG,"screenWidth :"+ WINDOW_WIDTH);
        //窗口高度

        scHeight = dm.heightPixels;
       //og.i(TAG, "screenHeight:"+ WINDOW_HEIGHT);
    }

    public void onGameStart(View view){
        Intent intent=new Intent(this, gameActivity.class);
        startActivity(intent);
    }

    public void LogIn(View view){
        Intent intent=new Intent(this,LogIn.class);
        startActivity(intent);
    }



}