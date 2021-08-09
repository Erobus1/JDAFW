package project.bot.commands.managers;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import project.bot.commands.builders.SlashCommand;
import project.bot.commands.builders.SubCommand;
import project.bot.commands.builders.SubCommandGroup;
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
        SlashCommand actualCommand;
        if (event.getSubcommandGroup() != null) {
            String subCmdGroupName = event.getSubcommandGroup();
            SubCommandGroup subCmdGroup = command.getCommandData().getSubCommandGroups().stream().filter(e -> e.getName().equals(subCmdGroupName)).findFirst().get();
            actualCommand = subCmdGroup.getSubCommands().stream().filter(s -> s.getCommandData().getName().equals(event.getSubcommandName())).findFirst().get();
        } else if (event.getSubcommandName() != null) {
            actualCommand = command.getCommandData().getSubCommands().stream().filter(s -> s.getCommandData().getName().equals(event.getSubcommandName())).findFirst().get();
        } else {
            actualCommand = command;
        }

        try {
            filter(event, actualCommand);
        } catch (Exception e) {
            PresetBuilder builder = new PresetBuilder(PresetType.ERROR, e.getMessage());
            event.replyEmbeds(builder.build()).setEphemeral(true).queue();
            return;
        }

        PresetBuilder errorBuilder = new PresetBuilder(PresetType.ERROR, "errormsg", "Something went wrong...");
        try {
            actualCommand.run(event);
        } catch (Throwable e) {
            errorBuilder.setDescription("```"+e.getMessage()+"```");
            if (!event.isAcknowledged()) {
                event.replyEmbeds(errorBuilder.build()).setEphemeral(true).queue();
            } else {
                event.getHook().editOriginalEmbeds(errorBuilder.build()).queue();
            }
        }
    }

    private static void filter(SlashCommandEvent event, SlashCommand command) throws Exception {

        if (command.getCommandData().getRank() != Rank.NONE) {
            if (!event.isFromGuild()) throw new Exception("You can only use this command in a guild!");
            Rank cmdRank = command.getCommandData().getRank();
            if (!cmdRank.getCallback().hasPermission(event.getMember())) throw new Exception("You need to have the following permission in order to use this command: `\" + cmdRank.getName() + \"`");
        }

    }
}
