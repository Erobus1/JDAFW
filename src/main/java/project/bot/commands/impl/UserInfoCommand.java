package project.bot.commands.impl;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import project.bot.commands.builders.CommandArgument;
import project.bot.commands.builders.CommandArguments;
import project.bot.commands.builders.CommandData;
import project.bot.commands.builders.SlashCommand;

public class UserInfoCommand extends SlashCommand {
    @Override
    public CommandData getCommandData() {
        return new CommandData()
                .name("user")
                .description("Retrieve information about a user")
                .args(new CommandArguments(
                new CommandArgument("user", "User to get information of", false, OptionType.STRING)
        ))
                .testOnly(true);
    }

    @Override
    public void run(SlashCommandEvent event) {
        User user;
        try {
            user = event.getJDA().retrieveUserById(event.getOption("user").getAsString()).complete();
        } catch (Throwable e) {
            event.reply("Not found").queue();
            return;
        }
        event.reply(user.getName()).queue();
    }
}
