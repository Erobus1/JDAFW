package project.bot.commands.impl.subCommands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import project.bot.commands.builders.CommandData;
import project.bot.commands.builders.SubCommand;

public class SubTestTestCommand extends SubCommand {
    @Override
    public CommandData getCommandData() {
        return new CommandData()
                .name("testest")
                .description("heya");
    }

    @Override
    public void run(SlashCommandEvent event) {
        event.reply("heya").queue();
    }
}
