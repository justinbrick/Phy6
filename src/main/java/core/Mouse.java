package core;

import static org.lwjgl.glfw.GLFW.*;

/**
 * This is how we'll be getting events or information from the mouse.
 */
public class Mouse
{
    private static Mouse current; // Our current mouse.
    private double mouseX, mouseY, lastX, lastY;
    private boolean[] mouseButtonDown = new boolean[3];
    private boolean beingHeld = false;

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

    public static void updateMousePos(long window, double mouseX, double mouseY)
    {
        Mouse mouse = get();
        mouse.lastX = mouse.mouseX;
        mouse.lastY = mouse.mouseY;
        mouse.mouseX = mouseX;
        mouse.mouseY = mouseY;
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
