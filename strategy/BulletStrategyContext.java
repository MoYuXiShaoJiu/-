package com.example.aircraftwar.JavaCode.edu.hitsz.strategy;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.EliteEnemy;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.HeroAircraft;

public class BulletStrategyContext {
    private Strategy strategy;

    public BulletStrategyContext( Strategy strategy){
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public void executeStrategyHero(HeroAircraft heroAircraft){
         strategy.doOperationHero(heroAircraft);
    }

    public void executeStrategyElite(AbstractAircraft eliteEnemy){
        strategy.doOperationElite(eliteEnemy);
    }


}
