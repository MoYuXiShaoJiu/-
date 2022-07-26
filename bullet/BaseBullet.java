package com.example.aircraftwar.JavaCode.edu.hitsz.bullet;

//import com.example.aircraftwar.JavaCode.edu.hitsz.application.Main;
import com.example.aircraftwar.JavaCode.edu.hitsz.basic.AbstractFlyingObject;
import static com.example.aircraftwar.MainActivity.scHeight;
import static com.example.aircraftwar.MainActivity.scWidth;
/**
 * 子弹类。
 * 也可以考虑不同类型的子弹
 *
 * @author hitsz
 */
public class BaseBullet extends AbstractFlyingObject {

    private int power = 10;

    public BaseBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY);
        this.power = power;
    }

    @Override
    public void forward() {
        super.forward();

        // 判定 x 轴出界
        if (locationX <= 0 || locationX >= scWidth) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= scHeight ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }

    public int getPower() {
        return power;
    }
}
