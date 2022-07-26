package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.aircraftwar.R;

public class RankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        String str1="";
        EditText editText1=(EditText) findViewById(R.id.InputInfo);
        str1=editText1.getText().toString();
        System.out.println(str1);
    }
    public  void GoToRank(View view)
    {
        Intent toRank=new Intent(this,showRank.class);
        startActivity(toRank);
        String str2="";
        EditText editText1=(EditText) findViewById(R.id.InputInfo);
        str2=editText1.getText().toString();
        System.out.println(str2);//这里验证已经取得了这个值
        //接下来就是怎么把排行榜搞出来

    }


}