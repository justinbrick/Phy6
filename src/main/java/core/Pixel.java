package core;

import java.awt.*;

public class Pixel extends Entity
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

    public void tick()
    {
        doLogic();
    }

    public void doLogic()
    {

    }

    public Vector2 getVelocity()
    {
        return this.velocity;
    }

    public void setVelocity(Vector2 vel)
    {
        this.velocity = vel;
    }
}
