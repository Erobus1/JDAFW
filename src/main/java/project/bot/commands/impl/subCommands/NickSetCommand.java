package project.bot.commands.impl.subCommands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import project.bot.commands.builders.CommandArgument;
import project.bot.commands.builders.CommandArguments;
import project.bot.commands.builders.CommandData;
import project.bot.commands.builders.SubCommand;
import project.sys.util.embeds.PresetBuilder;
import project.sys.util.embeds.PresetType;
import project.sys.util.permission.Rank;


public class NickSetCommand extends SubCommand {
    @Override
    public CommandData getCommandData() {
        return new CommandData()
                .name("set")
                .description("Set a user's nickname")
                .rank(Rank.MODERATOR)
                .args(
                        new CommandArguments(
                                new CommandArgument("user", "User to change nickname")
                                .type(OptionType.USER)
                                .optional(false),
                                new CommandArgument("nickname", "The new nickname")
                                .type(OptionType.STRING)
                                .optional(false)
                        )
                );
    }

    @Override
    public void run(SlashCommandEvent event) {
        event.deferReply().queue();
        Member member = event.getGuild().retrieveMember(event.getOption("user").getAsUser()).complete();
        member.modifyNickname(event.getOption("nickname").getAsString()).queue();
        event.getHook().editOriginalEmbeds(new PresetBuilder(PresetType.SUCCESS, "Successfully changed their' nickname").build()).queue();
    }
}
