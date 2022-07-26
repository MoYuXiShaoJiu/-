package com.example.aircraftwar.JavaCode.edu.hitsz.bullet;

import com.example.aircraftwar.JavaCode.edu.hitsz.observer.Subscriber;

/**
 * @Author hitsz
 */
public class EnemyBullet extends BaseBullet implements Subscriber {

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }


    @Override
    public void update() {
        vanish();
    }
}
