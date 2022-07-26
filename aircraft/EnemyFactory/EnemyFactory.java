package com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.EnemyFactory;

import com.example.aircraftwar.JavaCode.edu.hitsz.aircraft.AbstractAircraft;


/**敌机工厂接口**/
public interface EnemyFactory {

     AbstractAircraft createEnemy(int locationX, int locationY, int speedX, int speedY, int hp);
}
