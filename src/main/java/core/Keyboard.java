package core;
import static org.lwjgl.glfw.GLFW.*;

public class Keyboard
{
    private static Keyboard current;

    public Keyboard()
    {

    }

    /**
     * Hooks up with GLFW to take key input.
     * @param window The window that made this call.
     * @param key The key that is pressed (SEE GLFW ENUMS)
     * @param code Platform specific scancode (???)
     * @param action Pressed/Released
     * @param mods Modifier bits
     */
    public static void keyPressedCallback(long window, int key, int code, int action, int mods)
    {
        if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
        {
            glfwSetWindowShouldClose(window, true);
        }
        if (key >= GLFW_KEY_0 && key <= GLFW_KEY_9)
        {
            Game.changePixel(key);
        }
    }
}
