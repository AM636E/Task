package ua.regi.rovno.Task.lsystem.rules;

public class ReplaceRule implements Rule {

    private final String target;
    private final char source;

    public ReplaceRule(char source, String target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public String process(char ch) {
        if(this.source == ch) {
            return target;
        }

        return "";
    }
}
