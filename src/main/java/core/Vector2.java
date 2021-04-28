package core;

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
