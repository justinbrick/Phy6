package core;

public class Game
{



    // Every time a frame runs,
    public static void update()
    {
        Entity.update(); // Update first, then render.
        Entity.render();
        Time.updateDeltaTime();
    }
}
