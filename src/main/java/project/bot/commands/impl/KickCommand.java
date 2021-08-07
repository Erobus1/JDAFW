package project.bot.commands.impl;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import project.bot.commands.builders.CommandArgument;
import project.bot.commands.builders.CommandArguments;
import project.bot.commands.builders.CommandData;
import project.bot.commands.builders.SlashCommand;
import project.sys.util.embeds.PresetBuilder;
import project.sys.util.embeds.PresetType;
import project.sys.util.permission.Rank;

public class KickCommand extends SlashCommand {
    @Override
    public CommandData getCommandData() {
        return new CommandData()
                .name("kick")
                .description("Kick a guild member")
                .rank(Rank.MODERATOR)
                .testOnly(true)
                .args(new CommandArguments(
                        new CommandArgument("member", "Member that should be kicked", false, OptionType.USER),
                        new CommandArgument("reason", "State why you decide to kick that user", true, OptionType.STRING)
                ));
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void run(SlashCommandEvent event) {
        Member member = event.getOption("member").getAsMember();
        AuditableRestAction<Void> kick;
        try {
            kick = member.kick();
        } catch (Throwable e) {
            PresetBuilder builder = new PresetBuilder(PresetType.ERROR, "I can't kick that member! Reason: ```\n" + e.getMessage() + "```");
            event.replyEmbeds(builder.build()).setEphemeral(true).queue();
            return;
        }

        if (event.getOption("reason") != null) kick.reason(event.getOption("reason").getAsString());
        kick.queue();

        PresetBuilder builder = new PresetBuilder(PresetType.SUCCESS, member.getAsMention() + " was kicked from this guild!");
        if (event.getOption("reason") != null) builder.addField("Reason", event.getOption("reason").getAsString());

        event.replyEmbeds(builder.build()).queue();

    }
}
