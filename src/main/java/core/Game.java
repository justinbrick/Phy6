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
            float mouseX = (float) Mouse.getMouseX() / 5;
            float mouseY = (float) Mouse.getMouseY() / 5 - 1;
            new SandPixel(new Vector2(mouseX, mouseY));
            new SandPixel(new Vector2(mouseX + 1, mouseY));
            new SandPixel(new Vector2(mouseX - 1, mouseY));

        }
    }

    public static void place()
    {

    }

    public static void changePixel(int key)
    {

    }
}
