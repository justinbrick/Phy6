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
    private long window; // The handle for the window (GLFW)
    private static final int windowWidth = 1200;
    private static final int windowHeight = 700;

    // These might change in the future? I don't think so but better to encapsulate them than refactor.
    public static int getWindowHeight()
    {
        return windowWidth;
    }

    public static int getWindowWidth()
    {
        return windowHeight;
    }

    public void run()
    {
        System.out.println("Startup!");
        System.out.println("LWJGL Version: " + Version.getVersion());

        init();
        loop(); // When this exits, that means that they have closed the window.

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
        // Allows interop (essential)
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION); // We want to do projection during this entire project.
        glLoadIdentity();
        glOrtho(0, 1200.0, 0, 700.0, -1, 1); // see https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glOrtho.xml

        // Will continue running until closed.
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(window); // swap the color buffers

            Game.update(); // Do game logic.

            glfwPollEvents(); // Check for window events
        }
    }
}
