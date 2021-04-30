package core;

public class Time
{
    private static final float TIME_MIN = 0.0167f; // We want this to be ~60 fps.
    private static final float TIME_MAX = 0.1f; // Don't go too high or else this'll break stuff.
    private static long lastTime = System.nanoTime();
    private static float deltaTime = 0f;

    public static void updateDeltaTime()
    {
        long currentTime = System.nanoTime();
        deltaTime = (currentTime - lastTime) / 1000000000f; // TODO: Find something better for this.

        if (deltaTime < TIME_MIN)
        {
            try
            {
                Thread.sleep((long)((TIME_MIN - deltaTime)*1000f));
                deltaTime = TIME_MIN;
            }
            catch (InterruptedException e) { } // I honestly don't know what would be standard to do in this case-scenario.
        }
        else if (deltaTime > TIME_MAX)
            deltaTime = TIME_MAX;

        lastTime = currentTime;
    }

    public static float getDeltaTime()
    {
        return deltaTime;
    }
}
