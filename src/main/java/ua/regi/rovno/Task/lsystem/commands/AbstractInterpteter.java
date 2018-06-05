package ua.regi.rovno.Task.lsystem.commands;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractInterpteter implements  CommandInterpteter {
    protected static Map<Character, Command> Cache = new HashMap<>();
}
