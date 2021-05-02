package core;

public class Game
{

    // Every time a frame runs
    public static void update()
    {
        Time.updateDeltaTime();
        Entity.render();
        Entity.update();
    }

    public static void place()
    {

    }

    public static void changePixel(int key)
    {

    }
}
