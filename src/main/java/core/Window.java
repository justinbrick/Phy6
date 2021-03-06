package core;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Most of this part is in the introduction to LWJGL, the rest will mostly be my code.
 */

public class Window
{
    private static long window; // The handle for the window (GLFW)
    private static int points = 0;
    private static String pixelType = "Water Pixel";
    private static String title = String.format("Phy6 - Currently Selected: %s - Points: %d", pixelType, points);
    private static final int windowWidth = 1200;
    private static final int windowHeight = 700;

    // These might change in the future? I don't think so but better to encapsulate them than refactor.
    public static int getWindowHeight()
    {
        return windowHeight;
    }

    public static int getWindowWidth()
    {
        return windowWidth;
    }


    public static void setPoints(int amount)
    {
        points = amount;
        updateWindow();
    }


    public static void updatePixelType(String pixelName)
    {
        pixelType = pixelName;
    }

    // This will be called to refresh the name afterwards.
    private static void updateWindow()
    {
        title = String.format("Phy6 - Currently Selected: %s - Points: %d", pixelType, points);
        glfwSetWindowTitle(window, title);
    }

    public void run()
    {
        System.out.println("Startup!");
        System.out.println("LWJGL Version: " + Version.getVersion());

        init();
        loop(); // When this exits, that means that they have closed the window.

        System.out.println("Goodbye!");
        glfwFreeCallbacks(window); // Free all callbacks.
        glfwDestroyWindow(window); // Now destroy the window.

        glfwTerminate(); // Get rid of GLFW.
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // we don't want this resized (just don't have enough time to deal with that)

        // Create the window
        window = glfwCreateWindow(1200, 700, "Phy6", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setting up the callbacks.
        glfwSetMouseButtonCallback(window, Mouse::updateMouseButton);
        glfwSetCursorPosCallback(window, Mouse::updateMousePos);
        glfwSetKeyCallback(window, Keyboard::keyPressedCallback);

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() )
        {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
        // Make the window visible
        glfwShowWindow(window);
    }

    private void loop()
    {
        GL.createCapabilities(); // Allows interop (essential)
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Setting the clear color to this
        glMatrixMode(GL_PROJECTION); // We want to do projection during this entire project
        glLoadIdentity(); // Load the identity (???)
        glOrtho(0, 240, 0, 140, -1, 1); // see https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glOrtho.xml
        while ( !glfwWindowShouldClose(window) )
        {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            Game.update(); // Do game logic.
            glfwPollEvents(); // Check for input
            glfwSwapBuffers(window); // swap the color buffers
        }
    }
}
