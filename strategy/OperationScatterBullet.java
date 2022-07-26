package com.example.aircraftwar.JavaCode.edu.hitsz.strategy;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.EliteEnemy;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.HeroAircraft;

public class OperationScatterBullet implements Strategy{
    @Override
    public void doOperationHero(HeroAircraft heroAircraft) {
        heroAircraft.changeShootNum(6);
    }

    @Override
    public void doOperationElite(AbstractAircraft eliteEnemy){
        if(eliteEnemy.getAcIndex()==3){
            eliteEnemy.changeShootNum(1);
        }
        else{
            eliteEnemy.changeShootNum(5);
        }
    }
}
