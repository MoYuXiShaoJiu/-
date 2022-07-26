package com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyFactory;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.EliteEnemy;


/**精英敌机工厂**/
public class EliteEnemyFactory implements EnemyFactory{
    @Override
    public AbstractAircraft createEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        return new EliteEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
