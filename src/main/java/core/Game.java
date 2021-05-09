package core;

import static org.lwjgl.glfw.GLFW.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

public class Game
{
    // Making using of Reflection
    public static Class currentlySelectedType = SandPixel.class;

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

            // This was pretty clever, but might not be good for if I want to move over to compiled languages
            // as this heavily depends on reflection. Who knows, maybe there's an equivalent to this in C++?
            try
            {
                Constructor construct = currentlySelectedType.getConstructor(Vector2.class);
                construct.newInstance(new Vector2(mouseX, mouseY));
                construct.newInstance(new Vector2(mouseX + 1, mouseY));
                construct.newInstance(new Vector2(mouseX - 1, mouseY));
            }
            catch (NoSuchMethodException e)
            {
                System.err.println("Could not find constructor for class!");
                return;
            }
            catch (InstantiationException e)
            {
                System.err.println("Could not instantiate");
            }
            catch (InvocationTargetException e)
            {
                System.err.println("Invocation Target except");
            }
            catch (Exception e)
            {
                System.err.println("Other except");
            }
        }
    }

    public static void place()
    {

    }

    public static void changePixel(int key)
    {
        switch (key)
        {
            case GLFW_KEY_1:
                currentlySelectedType = SandPixel.class;
                break;
            case GLFW_KEY_2:
                currentlySelectedType = WaterPixel.class;
                break;
            case GLFW_KEY_3:
                currentlySelectedType = WoodPixel.class;
                break;
        }
    }
}
