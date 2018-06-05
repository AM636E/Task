package ua.regi.rovno.Task;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import ua.regi.rovno.Task.lsystem.LSystem;
import ua.regi.rovno.Task.lsystem.Turtle;
import ua.regi.rovno.Task.lsystem.commands.CommandFlyweight;
import ua.regi.rovno.Task.lsystem.commands.SimpleInterpreter;
import ua.regi.rovno.Task.lsystem.rules.ReplaceRule;
import ua.regi.rovno.Task.lsystem.rules.Rule;

import java.nio.*;
import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;


public class Main {

    private long window;

    Turtle turtle;
    Turtle turtle2;


    public static void main(String[] args) {
        new Main().run();
    }

    private void initSimpleTurtle() {
        Rule[] ruleset = new Rule[6];
        ruleset[0] = new ReplaceRule('A', "ABA");
        ruleset[1] = new ReplaceRule('B', "BBB");
        ruleset[2] = new ReplaceRule('[', "[");
        ruleset[3] = new ReplaceRule(']', "]");
        ruleset[4] = new ReplaceRule('$', "$");
        ruleset[5] = new ReplaceRule('S', "A");
        LSystem system = new LSystem("[S]$", ruleset);

        turtle2 = new Turtle(system, new SimpleInterpreter(), new PVector(400, -200));
    }

    private void initTreeTurtle() {
        Rule[] ruleset = new Rule[3];
        ruleset[0] = new ReplaceRule('S', "F");
        ruleset[1] = new ReplaceRule('F', "FF+[+F-F-F]-[-F+F+F]");
        ruleset[2] = new ReplaceRule('$', "$");
        LSystem system = new LSystem("S$", ruleset);

        turtle = new Turtle(system, new CommandFlyweight(), new PVector(-0, - 0));
    }


    private void run() {
        // Cantor set
        initSimpleTurtle();

        // It doesn't work yet.
        initTreeTurtle();
        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("");
        }

        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(1200, 600, "Kinda LSystem!", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
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

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

        List<PVector[]> lines = turtle.draw(2);
        List<PVector[]> cantorLines = turtle2.draw(10);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window)) {
            // Actual OpenGL calls
            glClear(GL_COLOR_BUFFER_BIT);
            glDisable(GL_DEPTH_TEST);
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(-600, 600, -300, 300, 0, 1);
            glColor3f(0, 0, 0);

            renderLines(lines);
            renderLines(cantorLines);
            drawCircle(-300, 100, 50);

            // Swap buffers and look for events
            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void line(float x, float y, float len) {
        glBegin(GL_LINES);

        glVertex2f(x, y);
        glVertex2f(x + len, y);

        glEnd();
    }

    private void cantor(float x, float y, float len) {
        if (len < 1) return;
        line(x, y, len);

        y += 1;

        cantor(x - len * 2, y + len * 2, len / 3);
        cantor(x + len * 2 / 3, y + len / 3, len / 3);

        cantor(x + len / 2, y, len / 3);
        cantor(x + len * 2 / 3 + 10, y - len / 2, len / 3);
        cantor(x + len * 2 / 3 + len * 3, y - len / 2, len / 3);
    }

    private void circle(float x, float y, float radius) {
        glBegin(GL_LINE_LOOP);
        double b = 128;

        for (double i = 0; i < 2 * PI; i = i + ((2 * PI) / b)) {
            glVertex2d(x + radius * cos(i), y + radius * sin(i));
        }
        glEnd();
    }

    private void renderLines(List<PVector[]> lines) {
        for (PVector[] l : lines) {
            glBegin(GL_LINES);
            glVertex2d(l[0].getX(), l[0].getY());
            glVertex2d(l[1].getX(), l[1].getY());
            glEnd();
        }
    }

    private void drawCircle(float x, float y, float radius) {
        circle(x, y, radius);
        if (radius > 2) {
            drawCircle(x + radius / 2, y, radius / 2);
            drawCircle(x - radius / 2, y, radius / 2);
            drawCircle(x, y + radius / 2, radius / 2);
            drawCircle(x, y - radius / 2, radius / 2);
        }
    }
}
