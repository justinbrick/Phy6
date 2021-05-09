package core;


import java.util.Vector;

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

    /* Blank Constructor */
    public Vector2()
    {
        this.x = 0;
        this.y = 0;
    }

    /* Normal Constructor */
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /* Copy Constructor */
    public Vector2(Vector2 vector)
    {
        setX(vector.getX());
        setY(vector.getY());
    }

    /* Setters and Getters */
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

    /**
     * These functions have been made to prevent the redundant code I made earlier in the Entity class.
     */
    public Vector2 left()
    {
        Vector2 vec = new Vector2(this);
        vec.setX(vec.getX() - 1);
        return vec;
    }

    public Vector2 right()
    {
        Vector2 vec = new Vector2(this);
        vec.setX(vec.getX() + 1);
        return vec;
    }

    public Vector2 up()
    {
        Vector2 vec = new Vector2(this);
        vec.setY(vec.getY() + 1);
        return vec;
    }

    public Vector2 down()
    {
        Vector2 vec = new Vector2(this);
        vec.setY(vec.getY() - 1);
        return vec;
    }

    /**
     * Subtracts from the current vector the values of the one given.
     * @param vec the vector we are subtracting this one with.
     */
    public void subtract(Vector2 vec)
    {
        x -= vec.getX();
        y -= vec.getY();
    }

    /**
     * Adds to the current vector the values of the one given.
     * @param vec the vector we are adding this one with.
     */
    public void add(Vector2 vec)
    {
        x += vec.getX();
        y += vec.getY();
    }

    /**
     * Sets this vector's X to this and returns an instance of the same vector.
     * @param x What will the X be?
     * @return Vector with this X value.
     */
    public Vector2 withX(float x)
    {
        Vector2 vec = new Vector2(this);
        vec.setX(x);
        return vec;
    }

    /**
     * Sets this vector's Y to this and returns an instance of the same vector.
     * @param y What will the Y be?
     * @return Vector with this Y value.
     */
    public Vector2 withY(float y)
    {
        Vector2 vec = new Vector2(this);
        vec.setY(y);
        return vec;
    }

    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
}
