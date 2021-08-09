package project.bot.commands.impl.subCommands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import project.bot.commands.builders.CommandArgument;
import project.bot.commands.builders.CommandArguments;
import project.bot.commands.builders.CommandData;
import project.bot.commands.builders.SubCommand;
import project.sys.util.embeds.PresetBuilder;

public class NickGetCommmand extends SubCommand {
    @Override
    public CommandData getCommandData() {
        return new CommandData()
                .name("get")
                .description("Get the nickname of a user")
                .args(
                        new CommandArguments()
                .add(new CommandArgument("user", "user to get the nickname of", false, OptionType.USER)));
    }

    @Override
    public void run(SlashCommandEvent event) {
        event.replyEmbeds(new PresetBuilder("Nickname: " + event.getOption("user").getAsMember().getEffectiveName()).setTitle("User nickname").build()).queue();
    }
}
