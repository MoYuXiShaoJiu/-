package com.example.aircraftwar.JavaCode.edu.hitsz.strategy;


import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyProduct.EliteEnemy;
import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.HeroAircraft;

public interface Strategy
{
    void doOperationHero(HeroAircraft heroAircraft);
    void doOperationElite(AbstractAircraft eliteEnemy);
}
