package core;

public class Time
{
    private static long lastTime = System.currentTimeMillis();
    private static float deltaTime = 0f;

    public static void updateDeltaTime()
    {
        deltaTime = (System.currentTimeMillis() - lastTime) * 0.001f;
        lastTime = System.currentTimeMillis();
    }

    public static float getDeltaTime()
    {
        return deltaTime;
    }
}
