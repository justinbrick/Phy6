package core;

import java.awt.*;
import java.util.Random;

public class SandPixel extends Entity
{

    private static Random random = new Random();

    public SandPixel(Vector2 pos)
    {
        super(PixelType.SAND, Color.YELLOW, pos);
    }

    public void tick()
    {
        double direction = random.nextDouble();
        if (isSpace(getPosition().down()))
            moveDown();
        else
        {
            if (direction > 0.5 && isSpace(getPosition().right().down()))
            {
                moveRight();
                moveDown();
            }
            else if (direction < 0.5 && isSpace(getPosition().left().down()))
            {
                moveLeft();
                moveDown();
            }
        }
        hasTicked = true;
    }
}
