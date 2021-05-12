package core;

import static org.lwjgl.glfw.GLFW.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Game
{
    private static int points = 0; // No destruction for pixels yet, instead let's give points based on how many pixels are on screen.
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

    /**
     * Check if the mouse has had any updates, and then place pixels in the world if there are any updates.
     */
    private static void checkForMouseUpdates()
    {
        if (Mouse.isLeftButtonDown())
        {
            float mouseX = (float) Mouse.getMouseX() / 5;
            float mouseY = (float) Mouse.getMouseY() / 5 - 1;

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

    /**
     * This is attached by keyboard, it will see what key is being pressed and change the pixel type accordingly.
     * @param key The GLFW Keycode
     */
    public static void changePixel(int key)
    {
        String type = "INVALID";
        switch (key)
        {
            case GLFW_KEY_1:
                currentlySelectedType = SandPixel.class;
                type = "Sand Pixel";
                break;
            case GLFW_KEY_2:
                currentlySelectedType = WaterPixel.class;
                type = "Water Pixel";
                break;
            case GLFW_KEY_3:
                currentlySelectedType = WoodPixel.class;
                type = "Wood Pixel";
                break;
        }
        Window.updatePixelType(type);
    }
}
