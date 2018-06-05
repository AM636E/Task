package ua.regi.rovno.Task.lsystem.commands;

import ua.regi.rovno.Task.PVector;

import java.util.Stack;

public class SimpleInterpreter extends AbstractInterpteter {

    private static Stack<PVector> LocationCache = new Stack<>();
    int len = 800;

    @Override
    public Command createCommand(char ch)  {

        switch (ch) {
            case 'A' : {
                return new LineCommand(false, len);
            }
            case 'B' : {
                return new TranslateCommand(false, len);
            }
            case '[' : {
                return new SaveCommand(LocationCache);
            }
            case ']' : {
                return new RestoreCommand(LocationCache);
            }

            case '$' : {
                len /= 3;
                return new TranslateCommand(true, 10);
            }
        }

        return null;
    }
}
