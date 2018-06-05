package ua.regi.rovno.Task.lsystem;

import ua.regi.rovno.Task.lsystem.rules.Rule;

public class LSystem {
    private final Rule[] ruleset;
    private String start;

    public LSystem(String start, Rule[] ruleset) {
        this.ruleset = ruleset;
        this.start = start;
    }

    public String nextGeneration() {
     //   return "F+-";
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < this.start.length(); i ++) {
            char ch = this.start.charAt(i);

            for(Rule r : this.ruleset) {
                builder.append(r.process(ch));
            }
        }

        return this.start = builder.toString();
    }
}
