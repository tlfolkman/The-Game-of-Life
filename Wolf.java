package folkman;

import java.awt.*;
import java.util.Random;

public class Wolf extends Creature implements Movable, Aware, Aggressor, Spawner {

    private int preferedDirection;
    boolean spawn;

    public Wolf()
    {
        preferedDirection = new Random().nextInt(4);
        _health = 10;
        spawn = false;
    }

    @Override
    Shape getShape()
    {
        return Shape.Square;
    }

    @Override
    Color getColor()
    {
        return new Color(128, 128, 128);
    }

    @Override
    Boolean isAlive()
    {
        return _health > 0;
    }

    @Override
    public void attack(Creature target)
    {
        if (target instanceof Animal)
        {
            target.takeDamage(5);

            if(!target.isAlive())
            {
                spawn = true;
                _health++;
            }
        }
    }

    @Override
    public void move()
    {
        switch(preferedDirection) {
            case 0:
                _location.x++;
                break;
            case 1:
                _location.x--;
                break;
            case 2:
                _location.y++;
                break;
            case 3:
                _location.y--;
                break;
            default:
                break;
        }
    }

    @Override
    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right)
    {
        switch(preferedDirection)
        {
            case 0:
                if(right instanceof Animal) {}

                else if (below instanceof Animal)
                {
                    preferedDirection = 2;
                }
                else if (left instanceof Animal)
                {
                    preferedDirection = 1;
                }
                else if (above instanceof Animal)
                {
                    preferedDirection = 3;
                }
                break;
            case 1:

                if(left instanceof Animal) {}

                else if (above instanceof Animal)
                {
                    preferedDirection = 3;
                }
                else if (right instanceof Animal)
                {
                    preferedDirection = 0;
                }
                else if (below instanceof Animal)
                {
                    preferedDirection = 2;
                }
                break;
            case 2:

                if(below instanceof Animal) {}

                else if (left instanceof Animal)
                {
                    preferedDirection = 1;
                }
                else if (above instanceof Animal)
                {
                    preferedDirection = 3;
                }
                else if (right instanceof Animal)
                {
                    preferedDirection = 0;
                }
                break;

            case 3:

                if(above instanceof Animal) {}

                else if (right instanceof Animal)
                {
                    preferedDirection = 0;
                }
                else if (below instanceof Animal)
                {
                    preferedDirection = 2;
                }
                else if (left instanceof Animal)
                {
                    preferedDirection = 1;
                }
                break;
        }

    }

    @Override
    public Creature spawnNewCreature() {
        if(spawn)
        {
            Wolf w = new Wolf();
            int x = this._location.x;
            int y = this._location.y;
            w.setLocation(new Point(--x, --y));
            spawn = false;
            return w;
        }

        return null;
    }
}
