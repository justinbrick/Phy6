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
        this.x = x;
        return new Vector2(this);
    }

    /**
     * Sets this vector's Y to this and returns an instance of the same vector.
     * @param y What will the Y be?
     * @return Vector with this Y value.
     */
    public Vector2 withY(float y)
    {
        this.y = y;
        return new Vector2(this);
    }

    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
}
