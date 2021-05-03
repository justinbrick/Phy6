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
        if (isSpaceBelow())
            moveDown();
        else
        {
            if (direction > 0.5 && isSpaceRight())
                moveRight();
            else if (direction < 0.5 && isSpaceLeft())
                moveLeft();
        }
        hasTicked = true;
    }
}
