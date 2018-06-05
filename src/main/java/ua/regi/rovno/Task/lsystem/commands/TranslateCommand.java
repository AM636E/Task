package ua.regi.rovno.Task.lsystem.commands;

import ua.regi.rovno.Task.PVector;

public class TranslateCommand implements Command {

    private final boolean up;
    private final float len;

    public TranslateCommand(boolean up, float len) {
        this.up = up;
        this.len = len;
    }
    

    @Override
    public PVector getNextCoords(PVector currentCoords, PVector prevCoords) {
        if(up) {
            return new PVector(currentCoords.getX(), currentCoords.getY() + len);
        }

        return new PVector(currentCoords.getX() - len, currentCoords.getY());
    }

    @Override
    public void init(Object[] args) {

    }

    @Override
    public boolean shouldDraw() {
        return false;
    }
}
