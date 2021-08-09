package project.bot.commands.impl;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import project.bot.commands.builders.CommandData;
import project.bot.commands.builders.SlashCommand;
import project.bot.commands.impl.subCommands.SubTestTestCommand;

public class SubTestCommand extends SlashCommand {
    @Override
    public CommandData getCommandData() {
        return new CommandData()
                .name("subtest")
                .description("test for sub commands")
                .testOnly(true)
                .subCommands(new SubTestTestCommand());
    }

    @Override
    public void run(SlashCommandEvent event) {

    }
}
