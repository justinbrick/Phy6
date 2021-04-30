package core;

import java.awt.*;

// TODO: Come back to this later when I have the mental capacity to think about this.
public class Pixel extends Entity
{
    public static final float GRAVITY_CONST = 10F;
    private Vector2 velocity;

    public Pixel()
    {
        super();
    }

    public Pixel(Color color, Vector2 pos)
    {
        super(color, pos);
    }

    public void tick()
    {
        doLogic();
    }

    public void doLogic()
    {

    }
}
