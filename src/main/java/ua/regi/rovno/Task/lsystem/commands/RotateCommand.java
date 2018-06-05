package ua.regi.rovno.Task.lsystem.commands;

import ua.regi.rovno.Task.PVector;

public class RotateCommand implements Command {
    private final int THETA = 90;
    private final int direction;

    public RotateCommand(int direction) {
        this.direction = direction;
    }

    @Override
    public PVector getNextCoords(PVector currentCoords, PVector prevCoords) {
        double t = direction > 0 ? 180 - THETA : THETA;
        double rad = 180 / Math.PI * t;
        float x = currentCoords.getX();
        float y = currentCoords.getY();
        float x2 = (float) (x * Math.cos(rad) - y * Math.sin(rad));
        float y2 = (float) (x * Math.sin(rad) + y * Math.cos(rad));

        return new PVector(x2, y2);
    }

    @Override
    public void init(Object[] args) {
    }

    @Override
    public boolean shouldDraw() {
        return true;
    }
}
