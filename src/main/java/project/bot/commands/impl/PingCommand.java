package project.bot.commands.impl;

import project.bot.commands.builders.CommandData;
import project.bot.commands.builders.SlashCommand;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class PingCommand extends SlashCommand {
    @Override
    public CommandData getCommandData() {
        return new CommandData().name("ping").description("Get's the bot's current ping.").testOnly(true);
    }

    @Override
    public void run(SlashCommandEvent event) {
        event.reply("Pong! " + event.getJDA().getRestPing().complete() + "ms").queue();
    }
}
