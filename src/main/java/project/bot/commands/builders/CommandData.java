package project.bot.commands.builders;

import project.sys.util.permission.Rank;

import java.util.Arrays;
import java.util.List;

public class CommandData {
    private String name;
    private String description;
    private CommandArguments args = new CommandArguments();
    private boolean testOnly = false;
    private Rank rank = Rank.NONE;
    private List<SubCommandGroup> subCommandGroups;
    private List<SubCommand> subCommands;

    public CommandData name(String name) {
        this.name = name.toLowerCase();
        return this;
    }
    public CommandData description(String description) {
        this.description = description;
        return this;
    }
    public CommandData args(CommandArguments args) {
        this.args = args;
        return this;
    }
    public CommandData testOnly(boolean testOnly) {
        this.testOnly = testOnly;
        return this;
    }
    public CommandData rank(Rank rank) {
        this.rank = rank;
        return this;
    }
    public CommandData subCommands(SubCommand... subCommands) {
        this.subCommands = Arrays.asList(subCommands);
        return this;
    }
    public CommandData subCommandGroups(SubCommandGroup... subCommandGroups) {
        this.subCommandGroups = Arrays.asList(subCommandGroups);
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandArguments getArgs() {
        return args;
    }

    public boolean isTestOnly() {
        return testOnly;
    }

    public Rank getRank() {
        return rank;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public List<SubCommandGroup> getSubCommandGroups() {
        return subCommandGroups;
    }
}
