package ua.regi.rovno.Task.lsystem.commands;

import ua.regi.rovno.Task.PVector;

public class RotateCommand implements Command {
    private final int THETA = 15;
    private final int direction;

    public RotateCommand(int direction) {
        this.direction = direction;
    }

    @Override
    public PVector getNextCoords(PVector currentCoords, PVector prevCoords) {
        double t = THETA * direction;
        double rad = Math.PI / 180 * t;

        PVector next = currentCoords.multiply(0.5f);
        float x = next.getX();
        float y = next.getY();
        float x2 = (float) (x * Math.cos(rad) - y * Math.sin(rad));
        float y2 = (float) (x * Math.sin(rad) + y * Math.cos(rad));

        return new PVector(x2, y2).multiply(1f);
    }

    @Override
    public void init(Object[] args) {
    }

    @Override
    public boolean shouldDraw() {
        return true;
    }
}
