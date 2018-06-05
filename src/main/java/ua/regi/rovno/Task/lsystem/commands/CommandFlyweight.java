package ua.regi.rovno.Task.lsystem.commands;

import ua.regi.rovno.Task.PVector;

import java.util.Stack;

public class CommandFlyweight extends AbstractInterpteter {
    private static Stack<PVector> LocationCache = new Stack<>();

    float len = 20;

    public Command createCommand(char ch) {
        Command command = null;
        Object[] args = null;
        switch (ch) {
            case 'F' :
                command = new LineCommand(true, len);
                break;
            case 'G':
                command = new TranslateCommand(true, len);
                break;
            case '+': {
                command = new RotateCommand(1);
                break;
            }
            case '-': {
                command = new RotateCommand(-1);
                break;
            }
            case '[' : {
                command = new SaveCommand(LocationCache);
                break;
            }
            case ']': {
                command = new RestoreCommand(LocationCache);
                break;
            }
            default:
                len *= 0.6;
                command = new LineCommand(true, 0);
        }

        if(args != null) {
            command.init(args);
        }

        return command;
    }
}
