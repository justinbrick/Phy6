package core;


import static org.lwjgl.opengl.GL11.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Base class for entities,
 * this is how we will do logic for each "pixel"
 */
public abstract class Entity
{
    private static ArrayList<Entity> entityList = new ArrayList<>();
    private Color color; // The color of this object.
    private Vector2 position; // The position of the object on the screen.

    public Entity()
    {
        entityList.add(this);
        color = new Color(255, 255, 255);
        position = new Vector2(0, 0);
    }

    public Entity(Color color, Vector2 position)
    {
        entityList.add(this);
        this.color = color;
        this.position = position;
    }

    // Rendering the pixels.
    public static void render()
    {
        for (Entity ent : entityList)
        {
            glClear(GL_COLOR_BUFFER_BIT); // Clear the colors.
            glColor3fv(ent.getColor().getRGBColorComponents(null));
        }
    }

    // The logic part.
    public static void update()
    {
        for (Entity ent : entityList)
        {
            ent.tick();
        }
    }

    public abstract void tick();

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }
}
