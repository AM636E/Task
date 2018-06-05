package ua.regi.rovno.Task;

import static org.lwjgl.opengl.GL11.*;


public class KochLine {

    private final PVector start;
    private final PVector end;

    public KochLine(PVector start, PVector end) {
        this.start = start;
        this.end = end;
    }

    public void draw() {
        glBegin(GL_LINES);

        glVertex2f(start.getX(), start.getY());
        glVertex2f(end.getX(), end.getY());

        glEnd();
    }
}