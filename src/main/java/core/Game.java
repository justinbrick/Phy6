package core;

public class Game
{
    // Allocates all the space for our entities, we'll iterate through this and do logic for each.
    //private static Entity[][] entityList = new Entity[Window.getWindowHeight()][Window.getWindowWidth()];

    public static Pixel currentlySelectedPixel;

    // Every time a frame runs,
    public static void update()
    {
        Time.updateDeltaTime();
        Entity.render();
        Entity.update();
    }

    public static void changePixel(int key)
    {

    }
}
