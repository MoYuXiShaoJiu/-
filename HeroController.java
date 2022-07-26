package com.example.aircraftwar.JavaCode.edu.hitsz;

import android.view.MotionEvent;
import android.view.View;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.HeroAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.application.Game;
import com.example.aircraftwar.JavaCode.edu.hitsz.application.ImageManager;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.HeroAircraft;

public class HeroController implements View.OnTouchListener {

    private static final String TAG = "123";
    private Game game;
    private HeroAircraft heroAircraft;
    private boolean isMoving;

    public HeroController(Game game, HeroAircraft heroAircraft) {
        this.game = game;
        this.heroAircraft = heroAircraft;
        game.setOnTouchListener(this);
        isMoving = false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if(     x < heroAircraft.getLocationX() + ImageManager.HERO_IMAGE.getWidth()/2 &&
                    x > heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth()/2 &&
                    y < heroAircraft.getLocationY() + ImageManager.HERO_IMAGE.getHeight()/2 &&
                    y > heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getHeight()/2 )
            {
                isMoving = true;
            }
        } else if(isMoving && motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
//            heroAircraft.setLocation(motionEvent.getX()-ImageManager.HERO_IMAGE.getWidth()/2,
//                    motionEvent.getY()-ImageManager.HERO_IMAGE.getHeight()/2);
            heroAircraft.setLocation(motionEvent.getX(),
                    motionEvent.getY());
        } else if(isMoving && motionEvent.getAction() == MotionEvent.ACTION_UP) {
            isMoving = false;
        }

        return true;
    }
}
