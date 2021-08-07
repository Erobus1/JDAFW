package project.bot.commands.managers;

import project.bot.commands.builders.SlashCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlashCommandManager {
    private HashMap<String, SlashCommand> commandMap = new HashMap<>();
    private List<SlashCommand> commandList = new ArrayList<>();

    public SlashCommand getCommand(String name) {
        return commandMap.get(name);
    }

    public SlashCommandManager register(SlashCommand... commands) {
        for (SlashCommand cmd : commands) {
            this.commandMap.put(cmd.getCommandData().getName(), cmd);
            this.commandList.add(cmd);
        }
        return this;
    }

    public List<SlashCommand> getCommandList() {
        return commandList;
    }
}
