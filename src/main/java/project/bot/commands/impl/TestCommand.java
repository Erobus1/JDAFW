package project.bot.commands.impl;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import project.bot.commands.builders.CommandData;
import project.bot.commands.builders.SlashCommand;
import project.sys.util.permission.Rank;

public class TestCommand extends SlashCommand {
    @Override
    public CommandData getCommandData() {
        return new CommandData()
                .name("test")
                .description("Test some cool stuff")
                .testOnly(true)
                .rank(Rank.DEVELOPER);
    }

    @Override
    public void run(SlashCommandEvent event) {
        event.reply("Here are cool buttons")
                .addActionRow(Button.primary("Cool", "Click me"))
                .queue();
    }
}
