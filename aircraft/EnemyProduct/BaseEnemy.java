package com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.bullet.BaseBullet;
import com.example.aircraftwar.JavaCode.edu.hitsz.observer.Subscriber;

import java.util.List;

public class BaseEnemy extends AbstractAircraft implements Subscriber
{

    public BaseEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public List<BaseBullet> shoot() {
        return null;
    }

    @Override
    public void changeShootNum(int i) {

    }

    public void update() {

    }
}
