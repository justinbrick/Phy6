package core;

import java.awt.*;
import java.util.Random;

public class WaterPixel extends Entity
{
    private static Random random = new Random();
    public WaterPixel(Vector2 pos)
    {
        super(PixelType.WATER, Color.BLUE, pos);
    }
    public void tick()
    {
        Entity ent = getEntityAbove();
        if (ent != null)
            if (ent.getPixelType() == PixelType.SAND)
                swapPixels(ent);

        float move = random.nextFloat();
        if (isSpaceBelow())
            moveDown();
        else if(isSpaceRight() && move > 0.5f)
            moveRight();
        else if (isSpaceLeft() && move < 0.5f)
            moveLeft();
    }
}
