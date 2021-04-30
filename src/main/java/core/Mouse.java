package core;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glFlush;

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
        drawMouseCursor ((int) (140 - mouseY / 5 - 1), (int)mouseX / 5);
    }

    private static void drawMouseCursor(int mouseX, int mouseY)
    {
        glColor4f(1, 1, 1, 0.1f);
        glBegin(GL_POLYGON);
        glVertex3d(mouseY, mouseX, 0); // Bottom Left
        glVertex3d(mouseY + 1, mouseX, 0); // Bottom Right
        glVertex3d(mouseY + 1, mouseX + 1, 0); // Top Right
        glVertex3d(mouseY, mouseX + 1, 0); // Top Left
        glEnd();
        glFlush();
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
