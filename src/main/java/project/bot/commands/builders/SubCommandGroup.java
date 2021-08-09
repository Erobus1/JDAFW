package project.bot.commands.builders;

import java.util.Arrays;
import java.util.List;

public class SubCommandGroup {
    private String name;
    private String description;
    private final List<SubCommand> subCommands;

    public SubCommandGroup(SubCommand... subCommands) {
        this.subCommands = Arrays.asList(subCommands);
    }

    public SubCommandGroup name(String name) {
        this.name = name;
        return this;
    }
    public SubCommandGroup description(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }
}
