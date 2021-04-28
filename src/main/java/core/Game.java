package core;

public class Game
{
    // Allocates all the space for our entities, we'll iterate through this and do logic for each.
    //private static Entity[][] entityList = new Entity[Window.getWindowHeight()][Window.getWindowWidth()];

    // Every time a frame runs,
    public static void update()
    {
        Time.updateDeltaTime();
        Entity.update(); // Update first, then render.
        Entity.render();
    }
}
