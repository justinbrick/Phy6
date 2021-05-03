package core;

import java.util.Vector;

public class Game
{

    // Every time a frame runs
    public static void update()
    {
        Time.updateDeltaTime();
        Entity.render();
        checkForMouseUpdates();
        Entity.update();
    }

    private static void checkForMouseUpdates()
    {
        if (Mouse.isLeftButtonDown())
        {
            new SandPixel(new Vector2((float) Mouse.getMouseX() / 5, (float) Mouse.getMouseY() / 5 - 1));
        }
    }

    public static void place()
    {

    }

    public static void changePixel(int key)
    {

    }
}
