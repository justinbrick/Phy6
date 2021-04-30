package core;


import static org.lwjgl.opengl.GL11.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Entity
 * There is a slight swap between the array and positions, this is because 2D arrays work different and I want
 * logic done from top right to bottom left. Because of this, I have abstracted the use of it to positions, and
 * if a pixel is wanted to move X, Y it will swap it to Y, X using set position and get position.
 * There will also be a moveTo function which will move it to the position but stop it if it comes into contact with
 * another pixel.
 */
public abstract class Entity
{
    public static Entity[][] entityList = new Entity[70][120]; // Maybe not hardcode this?
    private Color color = Color.WHITE; // The color of this object.
    private Vector2 position = null; // The position of the object on the screen.
    public boolean hasTicked = false;

    public Entity()
    {
        spawn(new Vector2(0,0));
    }

    public Entity(Color color, Vector2 position)
    {
        this.color = color;
        spawn(position);
    }

    /**
     * Spawns the entity at a given position.
     * THIS WILL NOT REMOVE THE PIXEL AT THE LAST POSITION! DO NOT USE TO MOVE!
     * @param position position to spawn
     */
    private void spawn(Vector2 position)
    {
        if (isOutOfBounds(position))
            moveIntoBounds(position);
        this.position = position;
        entityList[(int) position.getY()][(int) position.getX()] = this;
    }

    public static void moveIntoBounds(Vector2 position)
    {
        position.setX((position.getX() > entityList[0].length) ? entityList[0].length - 1 : position.getX());
        position.setX((position.getX() < 0) ? 0 : position.getX());
        position.setY((position.getY() > entityList[0].length) ? entityList[0].length - 1 : position.getY());
        position.setY((position.getY() < 0) ? 0 : position.getY());
    }

    public static boolean isOutOfBounds(Vector2 position)
    {
        if (position.getY() > entityList.length || position.getX() > entityList[0].length || position.getX() < 0 || position.getY() < 0)
            return true;
        return false;
    }

    /**
     * Renders all the pixels in the entity list.
     */
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
                glVertex3f(column, row, 0.0f); // Bottom Left
                glVertex3f(column + 1, row, 0.0f); // Bottom Right
                glVertex3f(column + 1, row + 1, 0.0f); // Top Right
                glVertex3f(column, row + 1, 0.0f); // Top Left
                glEnd();
                glFlush();
            }
        }
    }


    /**
     * Will do logic on all the pixels that are in the board.
     */
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
                if (ent == null || ent.hasTicked) continue;
                ent.position.setY(row);
                ent.position.setX(column);
                ent.tick();
                ent.doLogic();
                ent.hasTicked = true;
            }
        }
    }

    public static void debug()
    {
        new Pixel(Color.WHITE, new Vector2(5, 3));
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
        if (this.position == null) // If it is null then we have not placed it in yet.
            spawn(position);
        else
        {
            if (isOutOfBounds(position))
                moveIntoBounds(position);
            entityList[(int) this.position.getY()][(int) this.position.getX()] = null;
            entityList[(int) position.getY()][(int) position.getX()] = this;
        }
    }
}
