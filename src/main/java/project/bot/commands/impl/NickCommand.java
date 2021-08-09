package project.bot.commands.impl;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import project.bot.commands.builders.CommandData;
import project.bot.commands.builders.SlashCommand;
import project.bot.commands.builders.SubCommandGroup;
import project.bot.commands.impl.subCommands.NickGetCommmand;
import project.bot.commands.impl.subCommands.NickSetCommand;

public class NickCommand extends SlashCommand {
    @Override
    public CommandData getCommandData() {
        return new CommandData()
                .name("nick")
                .description("Handles nick actions")
                .subCommandGroups(
                        new SubCommandGroup(
                                new NickGetCommmand(),
                                new NickSetCommand()
                        ).name("user").description("Handles nick actions")
                )
                .testOnly(true);
    }

    @Override
    public void run(SlashCommandEvent event) {

    }
}
