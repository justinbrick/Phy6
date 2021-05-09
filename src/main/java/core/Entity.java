package core;


import static org.lwjgl.opengl.GL11.*;

import java.awt.*;
import java.security.spec.RSAOtherPrimeInfo;
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
    public static final float GRAVITY_CONST = 5.12f; // How fast gravity will change
    private static Entity[][] entityList = new Entity[140][240]; // Maybe not hardcode this?
    private PixelType pixelType;
    private Color color = Color.WHITE; // The color of this object.
    private Vector2 position; // The position of the object on the screen.
    private Vector2 velocity;
    public boolean onBoard;
    public boolean hasTicked;

    public Entity()
    {
        spawn(new Vector2(0,0));
    }

    public Entity(PixelType type, Color color, Vector2 position)
    {
        this.pixelType = type;
        this.color = color;
        this.velocity = new Vector2();
        spawn(position);
    }

    /**
     * Spawns the entity at a given position.
     * THIS WILL NOT REMOVE THE PIXEL AT THE LAST POSITION! DO NOT USE TO MOVE!
     * @param position position to spawn
     */
    public void spawn(Vector2 position)
    {
        if (isOutOfBounds(position))
            moveIntoBounds(position);
        if (entityList[(int)position.getY()][(int)position.getX()] != null)
        {
            return;
        }
        entityList[(int) position.getY()][(int) position.getX()] = this;
        this.position = new Vector2(position.getX(), position.getY());
        onBoard = true;
    }

    /**
     * Moves the given vector into bounds.
     * @param position the position that could potentially be out of bounds.
     */
    public static void moveIntoBounds(Vector2 position)
    {
        position.setX((position.getX() >= entityList[0].length) ? entityList[0].length - 1 : position.getX());
        position.setX((position.getX() < 0) ? 0 : position.getX());
        position.setY((position.getY() >= entityList.length) ? entityList.length - 1 : position.getY());
        position.setY((position.getY() < 0) ? 0 : position.getY());
    }

    /**
     * Checks if the position is in the bounds of the board.
     * @param position The position that we're querying.
     * @return Whether the position is on boards or not.
     */
    public static boolean isOutOfBounds(Vector2 position)
    {
        return position.getY() >= entityList.length || position.getX() >= entityList[0].length || position.getX() < 0 || position.getY() < 0;
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
                if (ent == null || !ent.isOnBoard()) continue;
                glColor3fv(ent.getColor().getRGBColorComponents(null));
                glBegin(GL_POLYGON);
                // Left Top Forward <-- Vertex renders
                glVertex3f(column, row, 0.0f); // Bottom Left
                glVertex3f(column + 1, row, 0.0f); // Bottom Right
                glVertex3f(column + 1, row + 1, 0.0f); // Top Right
                glVertex3f(column, row + 1, 0.0f); // Top Left
                glEnd();
                //glFlush(); //FORCERENDER
            }
        }
    }

    /**
     * Will do logic on all the pixels that are on the board.
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
                if (ent == null || ent.hasTicked || !ent.isOnBoard()) continue;
                ent.tick();
                ent.hasTicked = true;
            }
        }
    }

    public static Entity getEntityAt(Vector2 position)
    {
        if (isOutOfBounds(position)) return null;
        return entityList[(int)position.getY()][(int)position.getX()];
    }

    /**
     * If the pixel needs to do some logic, then they can do it in this method.
     */
    public abstract void tick();

    public PixelType getPixelType()
    {
        return pixelType;
    }

    /**
     * What color is the pixel?
     * @return the color.
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Sets the color of the entity.
     * @param color Which color you would like the entity to be.
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /**
     * What is the position of the entity?
     * @return the position.
     */
    public Vector2 getPosition()
    {
        return position;
    }

    /**
     * Sets the position of the pixel, if it is not in bounds then it will move it into bounds somewhere.
     * @param position The position we want to move the pixel.
     */
    public void setPosition(Vector2 position)
    {
        if (this.position == null)
        {
            spawn(position);
        }
        else
        {
            if (isOutOfBounds(position))
            {
                moveIntoBounds(position);
            }
            if (entityList[(int)position.getY()][(int)position.getX()] != null)
            {
                return;
            }

            entityList[(int) getPosition().getY()][(int) getPosition().getX()] = null;
            entityList[(int) position.getY()][(int) position.getX()] = this;
            this.position.setX(position.getX());
            this.position.setY(position.getY());
            onBoard = true;
        }
    }

    public Vector2 getVelocity()
    {
        return this.velocity;
    }

    public void setVelocity(Vector2 vel)
    {
        this.velocity = vel;
    }

    /**
     * Check whether the entity is on the board or not.
     * @return ^
     */
    public boolean isOnBoard()
    {
        return onBoard;
    }

    /**
     * Toggle whether the entity is on the board or not.
     * @param onBoard ^
     */
    public void setOnBoard(boolean onBoard)
    {
        this.onBoard = onBoard;
    }

    /**
     * Removes from the entity list and sets position to null.
     */
    public void delete()
    {
        entityList[(int)position.getY()][(int)position.getX()] = null;
        position = null;
        onBoard = false;
    }

    /**
     * Removes from entity list but does not set position to null.
     * Can use to get the last position of the pixel before it was removed.
     */
    public void remove()
    {
        entityList[(int)position.getY()][(int)position.getX()] = null;
        onBoard = false;
    }

    /**
     * Check if there is space at a certain position.
     * @param pos The position we're checking
     * @return Whether or not the space is available.
     */
    public static boolean isSpace(Vector2 pos)
    {
        if (isOutOfBounds(pos)) return false;
        return getEntityAt(pos) == null;
    }

    /**
     * Get the entity below this pixel, null if none.
     * @return The entity
     */
    public Entity getEntityBelow()
    {
        return getEntityAt(new Vector2(getPosition().getX(), getPosition().getY() - 1));
    }

    /**
     * Get the entity above this pixel, null if none.
     * @return The entity
     */
    public Entity getEntityAbove()
    {
        return getEntityAt(new Vector2(getPosition().getX(), getPosition().getY() + 1));
    }

    /**
     * Get the entity left of this pixel, null if none.
     * @return The entity
     */
    public Entity getEntityLeft()
    {
        return getEntityAt(new Vector2(getPosition().getX() - 1, getPosition().getY()));
    }

    /**
     * Get the entity right of this pixel, null if none.
     * @return The entity
     */
    public Entity getEntityRight()
    {
        return getEntityAt(new Vector2(getPosition().getX() + 1, getPosition().getY()));
    }


    public void tryMoveDown()
    {

        velocity.setX(velocity.getX() - GRAVITY_CONST);
        setPosition(new Vector2(getPosition().getX(), getPosition().getY() - 1));
    }

    public void tryMoveLeft()
    {
        setPosition(new Vector2(getPosition().getX() - 1, getPosition().getY()));
    }

    public void tryMoveRight()
    {
        setPosition(new Vector2(getPosition().getX() + 1, getPosition().getY()));
    }

    /**
     * Normal movement functions, will attempt to move in any cardinal direction.
     */
    public void moveDown()
    {
        // TODO: This instantiates 3 different Vector2 objects, find better method.
        setPosition(new Vector2(getPosition().getX(), getPosition().getY() - 1));
    }

    public void moveLeft()
    {
        setPosition(new Vector2(getPosition().getX() - 1, getPosition().getY()));
    }

    public void moveRight()
    {
        setPosition(new Vector2(getPosition().getX() + 1, getPosition().getY()));
    }

    /**
     * Swaps the places of two pixels.
     * @param pixel The other pixel that you're swapping this one with.
     */
    public void swapPixels(Entity pixel)
    {
        if (pixel == null)
        {
            System.err.println("Tried swapping null pixel!");
            return;
        }
        // Remove these both from the board.
        remove();
        pixel.remove();
        Vector2 ourPos = getPosition();
        Vector2 pixelPos = pixel.getPosition();
        Vector2 temp = new Vector2(pixel.getPosition());
        pixel.spawn(getPosition());
        spawn(temp);
    }
}
