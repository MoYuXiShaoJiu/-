package com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyFactory;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.BossEnemy;

public class BossEnemyFactory implements EnemyFactory{
    @Override
    public AbstractAircraft createEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        return new BossEnemy(locationX,locationY,speedX,speedY,hp);
    }
}
