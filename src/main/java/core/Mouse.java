package core;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;

/**
 * This is how we'll be getting events or information from the mouse.
 * TODO: Make static class (we don't need listeners in such a simple game)
 */
public class Mouse
{
    private static Mouse current; // Our current mouse.
    private double mouseX, mouseY, lastX, lastY;
    private boolean[] mouseButtonDown = new boolean[3];
    private boolean beingHeld = false;
    private static Pixel debugPixel = new Pixel(Color.WHITE, new Vector2(20, 20));

    public Mouse()
    {
        mouseX = 0;
        mouseY = 0;
        lastX = 0;
        lastY = 0;
    }

    public static Mouse get()
    {
        if (current == null) current = new Mouse();
        return current;
    }

    // Why are these doubles??
    public static void updateMousePos(long window, double mouseX, double mouseY)
    {
        Mouse mouse = get();
        mouse.lastX = mouse.mouseX;
        mouse.lastY = mouse.mouseY;
        mouse.mouseX = mouseX;
        mouse.mouseY = mouseY;
        //System.out.println(mouseX + " " + mouseY);
        debugPixel.setPosition(new Vector2((int)mouseX / 10, 69 - (int)mouseY / 10));
    }

    public static void updateMouseButton(long window, int button, int action, int mods)
    {
        Mouse mouse = get();
        if (button > mouse.mouseButtonDown.length || button < mouse.mouseButtonDown.length) return;
        if (action == GLFW_PRESS)
        {
            mouse.mouseButtonDown[button] = true;
        }
        else if (action == GLFW_RELEASE)
        {
            mouse.mouseButtonDown[button] = false;
        }
    }
}
