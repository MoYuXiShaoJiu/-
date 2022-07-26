package com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyFactory;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.MobEnemy;


/**普通敌机工厂**/
public class MobEnemyFactory implements EnemyFactory {
    @Override
    public AbstractAircraft createEnemy(int locationX, int locationY, int speedX, int speedY, int hp)
    {
        return new MobEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
