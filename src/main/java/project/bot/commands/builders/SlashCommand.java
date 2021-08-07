package project.bot.commands.builders;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public abstract class SlashCommand {
    public abstract CommandData getCommandData();
    public abstract void run(SlashCommandEvent event);
}
