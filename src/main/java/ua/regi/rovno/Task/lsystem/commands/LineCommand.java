package ua.regi.rovno.Task.lsystem.commands;

import ua.regi.rovno.Task.PVector;

public class LineCommand implements Command {
    private final boolean vertical;
    private final float len;

    public LineCommand(boolean vertical, float len) {
        this.vertical = vertical;
        this.len = len;
    }

    @Override
    public PVector getNextCoords(PVector currentCoords, PVector prevCoords) {
        if(this.vertical) {
            return new PVector(currentCoords.getX(), currentCoords.getY() + len);
        }

        return new PVector(currentCoords.getX() - len, currentCoords.getY());
    }

    @Override
    public void init(Object[] args) {

    }

    @Override
    public boolean shouldDraw() {
        return true;
    }
}
