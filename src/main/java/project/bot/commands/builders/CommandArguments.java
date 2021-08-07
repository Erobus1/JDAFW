package project.bot.commands.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandArguments {
    private HashMap<String, CommandArgument> args = new HashMap<>();
    private HashMap<String, CommandArgument> argsByIds = new HashMap<>();
    private int index = 0;

    public CommandArguments() {}
    public CommandArguments(CommandArgument... args) {
        for (CommandArgument arg : args) {
            add(arg);
        }
    }
    public CommandArguments add(CommandArgument arg) {
        arg.index(index);
        this.args.put(arg.getName(), arg);
        this.argsByIds.put(String.valueOf(index), arg);
        index++;
        return this;
    }
    public List<CommandArgument> getArgs() {
        List<CommandArgument> argList = new ArrayList<>();
        boolean exists = true;
        int i = 0;
        while (exists) {
            CommandArgument arg;
            if ((arg = this.argsByIds.get(String.valueOf(i))) != null) {
                argList.add(arg);
            } else {
                exists = false;
            }
            i++;
        }
        return argList;
    }


}
