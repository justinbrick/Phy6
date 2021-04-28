package core;

public class Time
{
    private static final float TIME_MIN = 0.0167f; // We want this to be ~60 fps.
    private static final float TIME_MAX = 0.1f; // Don't go too high or else this'll break stuff.
    private static long lastTime = System.currentTimeMillis();
    private static float deltaTime = 0f;

    public static void updateDeltaTime()
    {
        deltaTime = (System.currentTimeMillis() - lastTime) * 0.001f;
        if (deltaTime < TIME_MIN)
            deltaTime = TIME_MIN;
        else if (deltaTime > TIME_MAX)
            deltaTime = TIME_MAX;
        lastTime = System.currentTimeMillis();
    }

    public static float getDeltaTime()
    {
        return deltaTime;
    }
}
