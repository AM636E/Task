package ua.regi.rovno.Task.lsystem.commands;

import ua.regi.rovno.Task.PVector;

public interface Command {
    PVector getNextCoords(PVector currentCoords, PVector prevCoords);
    void init(Object[] args);
    boolean shouldDraw();
}
