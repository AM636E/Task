package ua.regi.rovno.Task.lsystem.commands;

import ua.regi.rovno.Task.PVector;

import java.util.Stack;

public class SaveCommand implements Command {

    private Stack<PVector> cache;

    public SaveCommand() {

    }

    public SaveCommand(Stack<PVector> cache) {
        this.cache = cache;
    }

    @Override
    public PVector getNextCoords(PVector currentCoords, PVector prevCoords) {
        cache.push(new PVector(currentCoords.getX(), currentCoords.getY()));
        return currentCoords;
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
