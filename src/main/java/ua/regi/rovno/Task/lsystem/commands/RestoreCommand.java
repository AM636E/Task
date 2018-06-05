package ua.regi.rovno.Task.lsystem.commands;

import ua.regi.rovno.Task.PVector;

import java.util.Stack;

public class RestoreCommand implements Command {

    private Stack<PVector> cache;

    public RestoreCommand() {}

    public RestoreCommand(Stack<PVector> cache) {
        this.cache = cache;
    }

    @Override
    public PVector getNextCoords(PVector currentCoords, PVector prevCoords) {
        return cache.pop();
    }

    @Override
    public void init(Object[] args) {
        cache = (Stack<PVector>)args[0];
    }

    @Override
    public boolean shouldDraw() {
        return false;
    }
}
