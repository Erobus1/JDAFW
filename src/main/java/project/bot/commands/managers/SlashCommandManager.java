package project.bot.commands.managers;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import project.bot.commands.builders.SlashCommand;
import project.sys.util.embeds.PresetBuilder;
import project.sys.util.embeds.PresetType;
import project.sys.util.permission.Rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlashCommandManager {
    private HashMap<String, SlashCommand> commandMap = new HashMap<>();
    private List<SlashCommand> commandList = new ArrayList<>();

    public SlashCommand getCommand(String name) {
        return commandMap.get(name);
    }

    public SlashCommandManager register(SlashCommand... commands) {
        for (SlashCommand cmd : commands) {
            this.commandMap.put(cmd.getCommandData().getName(), cmd);
            this.commandList.add(cmd);
        }
        return this;
    }

    public List<SlashCommand> getCommandList() {
        return commandList;
    }

    public void run(SlashCommandEvent event, SlashCommand command) {
        if (command.getCommandData().getRank() != Rank.NONE) {
            if (!event.isFromGuild()) {
                PresetBuilder builder = new PresetBuilder(PresetType.ERROR, "You can only use this command in a guild!", "Guild only");
                event.replyEmbeds(builder.build()).setEphemeral(true).queue();
                return;
            }
            Rank cmdRank = command.getCommandData().getRank();
            if (!cmdRank.getCallback().hasPermission(event.getMember())) {
                PresetBuilder builder = new PresetBuilder(PresetType.ERROR, "You need to have the following permission in order to use this command: `" + cmdRank.getName() + "`", "Insufficient permission!");
                event.replyEmbeds(builder.build()).setEphemeral(true).queue();
                return;
            }
        }
        command.run(event);
    }
}
