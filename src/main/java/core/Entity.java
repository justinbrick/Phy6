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
    public static Entity[][] entityList = new Entity[Window.getWindowHeight()][Window.getWindowWidth()];
    private Color color = Color.WHITE; // The color of this object.
    private Vector2 position = new Vector2(0,0); // The position of the object on the screen.
    public boolean hasTicked = false;

    public Entity()
    {
        entityList[0][0] = this;
    }

    public Entity(Color color, Vector2 position)
    {
        entityList[0][0] = this;
        this.color = color;
        this.position = position;
    }

    // Rendering the pixels.
    public static void render()
    {
        for (int row = 0; row < entityList.length; ++row)
        {
            for (int column = 0; column < entityList[row].length; ++column)
            {
                Entity ent = entityList[row][column];
                if (ent == null) continue;
                glClear(GL_COLOR_BUFFER_BIT); // Clear the colors.
                //glColor3fv(ent.getColor().getRGBColorComponents(null));
                glColor3f(0, 1, 0);
                glBegin(GL_POLYGON);
                // As I understand, the third value is the distance to and from the screen. We don't care about that.
                glVertex3f(2.0f, 4.0f, 0.0f); // Bottom Left
                glVertex3f(8.0f, 4.0f, 0.0f); // Top Left
                glVertex3f(8.0f, 6.0f, 0.0f); // Top Right
                glVertex3f(2.0f, 6.0f, 0.0f); // Bottom Right
                glEnd();
                glFlush();
            }
        }
    }

    // The logic part.
    public static void update()
    {
        for (Entity[] entColumn : entityList)
            for (Entity ent : entColumn)
                if (ent != null)
                    ent.hasTicked = false;

        for (int row = 0; row < entityList.length; ++row)
        {
            for (int column = 0; column < entityList[row].length; ++column)
            {
                Entity ent = entityList[row][column];
                if (ent == null) continue;
                ent.tick();
                ent.doLogic();
                ent.hasTicked = true;
            }
        }
    }

    public static void debug()
    {
        entityList[300][300] = new Pixel();
    }

    // This will be where pixels do their logic.
    public abstract void tick();

    // Not all entities will have logic, so this is not abstracted.
    // We also need to make sure these never go out of bounds.
    public void doLogic()
    {
        position.setX(position.getX() < 0 ? 0 : position.getX());
        position.setX(position.getX() > Window.getWindowWidth() ? 0 : position.getX());
        position.setY(position.getY() < 0 ? 0 : position.getY());
        position.setY(position.getY() < 0 ? Window.getWindowHeight() : position.getY());
    }

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
