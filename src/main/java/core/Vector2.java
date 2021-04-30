package core;

/**
 * Vector2
 *
 * The x and y are floats because this is going to be used for velocity but it is also being used for position,
 * explaining why I have casts to ints in all references requiring ints and being supplied these values.
 *
 */
public class Vector2
{
    private float x;
    private float y;

    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public Vector2 withX(float x)
    {
        this.x = x;
        return this;
    }

    public Vector2 withY(float y)
    {
        this.y = y;
        return this;
    }
}
