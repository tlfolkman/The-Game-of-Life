package folkman;

import java.awt.*;

public class Zombie extends Creature implements Aggressor, Movable {

    public Zombie()
    {
        _health = 20;
    }

    @Override
    public void attack(Creature target) {

        if (target instanceof Animal)
            target.takeDamage(5);

        if (target instanceof Wolf)
            target.takeDamage((10));
    }

    @Override
    Shape getShape() {
        return Shape.Square;
    }

    @Override
    Color getColor() {
        return new Color(0, 33, 135);
    }

    @Override
    Boolean isAlive() {
        return _health > 0;
    }

    @Override
    public void move() {
        _location.x++;
    }
}
