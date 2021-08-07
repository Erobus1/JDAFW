package project.bot.commands.impl;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.InteractionHook;
import project.bot.commands.builders.CommandData;
import project.bot.commands.builders.SlashCommand;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import project.sys.util.embeds.PresetBuilder;
import project.sys.util.embeds.PresetType;
import project.sys.util.permission.Rank;

import java.util.concurrent.TimeUnit;

public class PingCommand extends SlashCommand {
    @Override
    public CommandData getCommandData() {
        return new CommandData().name("ping").description("Get's the bot's current ping.").testOnly(true);
    }

    @Override
    public void run(SlashCommandEvent event) {
        event.replyEmbeds(new PresetBuilder(PresetType.NOTIFICATION, "Pong! " + event.getJDA().getRestPing().complete() + "ms").build()).queue();
    }
}
