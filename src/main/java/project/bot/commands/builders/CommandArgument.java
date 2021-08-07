package project.bot.commands.builders;


import net.dv8tion.jda.api.interactions.commands.OptionType;

public class CommandArgument {
    private String name;
    private String description;
    private boolean optional;
    private OptionType type;
    private int index;

    public CommandArgument() {}
    public CommandArgument(String name) {
        this.name = name;
    }
    public CommandArgument(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public CommandArgument(String name, String description, boolean optional) {
        this.name = name;
        this.description = description;
        this.optional = optional;
    }
    public CommandArgument(String name, String description, boolean optional, OptionType type) {
        this.name = name;
        this.description = description;
        this.optional = optional;
        this.type = type;
    }



    public CommandArgument name(String name) {
        this.name = name;
        return this;
    }
    public CommandArgument description(String description) {
        this.description = description;
        return this;
    }
    public CommandArgument optional(boolean optional) {
        this.optional = optional;
        return this;
    }
    public CommandArgument type(OptionType type) {
        this.type = type;
        return this;
    }
    public CommandArgument index(int index) {
        this.index = index;
        return this;
    }



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOptional() {
        return optional;
    }

    public OptionType getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }
}
