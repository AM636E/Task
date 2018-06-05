package ua.regi.rovno.Task.lsystem;

import ua.regi.rovno.Task.PVector;
import ua.regi.rovno.Task.lsystem.commands.Command;
import ua.regi.rovno.Task.lsystem.commands.CommandInterpteter;

import java.util.ArrayList;
import java.util.List;

public class Turtle {

    private final LSystem system;
    private CommandInterpteter interpteter;
    private PVector currentCoords;
    private PVector nextCoords;
    private PVector prevCoords;

    public Turtle(LSystem system, CommandInterpteter interpteter, PVector currentCoords) {
        this.system = system;
        this.interpteter = interpteter;
        this.currentCoords = currentCoords;
    }

    public List<PVector[]> draw(int generations) {
        List<PVector[]> result = new ArrayList<>();

        for (int i = 0; i < generations; i++) {
            String generation = this.system.nextGeneration();
            System.out.println(generation);
            int len = generation.length();
            for (int j = 0; j < len; j++) {

                char ch = generation.charAt(j);
                Command command = interpteter.createCommand(ch);
                nextCoords = command.getNextCoords(currentCoords, prevCoords);
                if (command.shouldDraw()) {
                    PVector[] arr = new PVector[2];
                    arr[0] = currentCoords;
                    arr[1] = nextCoords;
                    result.add(arr);
                }
                prevCoords = currentCoords;
                currentCoords = nextCoords;
            }
        }

        return result;
    }
}
