package core;

import java.awt.*;
import java.util.Vector;

public abstract class Pixel extends Entity
{
    public static final float GRAVITY_CONST = 10F; // The rate at which we want velocity to increase.
    private Vector2 velocity; // Our current pixel's velocity

    public Pixel()
    {
        super();
    }

    public Pixel(Color color, Vector2 pos)
    {
        super(color, pos);
    }

    public abstract void tick();

    public boolean isSpaceBelow()
    {
        if (isOutOfBounds(new Vector2(getPosition().getX(), getPosition().getY() - 1))) return false;
        return getEntityAt(new Vector2(getPosition().getX(), getPosition().getY() - 1)) == null;
    }


}
