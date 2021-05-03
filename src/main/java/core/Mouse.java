package core;

import java.awt.*;
import java.util.Vector;

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
    private static double mouseX = 0, mouseY = 0, lastX = 0, lastY = 0;
    private static boolean[] mouseButtonDown = new boolean[3];
    private static boolean beingHeld = false;


    // Why are these doubles??
    public static void updateMousePos(long window, double newMouseX, double newMouseY)
    {
        lastX = mouseX;
        lastY = mouseY;
        mouseX = newMouseX;
        mouseY = newMouseY;
        drawMouseCursor((int) (140 - mouseY / 5 - 1), (int)mouseX / 5);
    }

    public static double getMouseX()
    {
        return mouseX;
    }

    public static double getMouseY()
    {
        return Window.getWindowHeight() - mouseY;
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

        if (button > mouseButtonDown.length || button < 0) return;
        if (action == GLFW_PRESS)
        {
            mouseButtonDown[button] = true;
        }
        else if (action == GLFW_RELEASE)
        {
            mouseButtonDown[button] = false;
        }
    }

    public static boolean isLeftButtonDown()
    {
        return mouseButtonDown[0];
    }

    public static boolean isRightButtonDown()
    {
        return mouseButtonDown[1];
    }
}
