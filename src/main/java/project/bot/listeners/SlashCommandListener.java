package project.bot.listeners;

import project.Main;
import project.bot.commands.builders.SlashCommand;
import project.bot.commands.managers.SlashCommandManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        SlashCommandManager manager = Main.getCmdManager();
        SlashCommand cmd = manager.getCommand(event.getName());
        if (cmd != null) cmd.run(event);
    }
}
