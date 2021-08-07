package project;

import project.bot.commands.builders.CommandArgument;
import project.bot.commands.builders.SlashCommand;
import project.bot.commands.impl.PingCommand;
import project.bot.commands.impl.UserInfoCommand;
import project.bot.commands.managers.SlashCommandManager;
import project.bot.listeners.SlashCommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import project.sys.constants.Config;
import project.sys.internal.exceptions.InvalidTestGuildException;

import javax.security.auth.login.LoginException;

public class Main {

    private static JDABuilder builder;
    private static JDA jda;
    private static SlashCommandManager cmdManager;

    public static void main(String[] args) throws InvalidTestGuildException {
        try {
            boot();
        } catch(LoginException | InterruptedException e) {
            System.out.println("Couldn't log in:");
            e.printStackTrace();
        }
    }

    private static void boot() throws LoginException, InterruptedException, InvalidTestGuildException {
        builder = JDABuilder.createDefault(Config.getTOKEN())
                .setActivity(Activity.playing(Config.getACTIVITY()))
                .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);


        cmdManager = new SlashCommandManager();
        cmdManager.register(
                new PingCommand(),
                new UserInfoCommand()
        );
        registerListeners();
        jda = builder.build();
        jda.awaitReady();
        upsertCommands();
    }

    private static void registerListeners() {
        builder.addEventListeners(new SlashCommandListener());
    }

    private static void upsertCommands() throws InvalidTestGuildException {
        Guild testGuild = jda.getGuildById(Config.getTestGuild());
        if (testGuild == null) throw new InvalidTestGuildException("ddd");
        for (SlashCommand cmd : cmdManager.getCommandList()) {
            CommandData command = new CommandData(cmd.getCommandData().getName(), cmd.getCommandData().getDescription());
            for (CommandArgument arg : cmd.getCommandData().getArgs().getArgs()) {
                command.addOption(arg.getType(), arg.getName(), arg.getDescription(), !arg.isOptional());
            }
            if (cmd.getCommandData().isTestOnly()) {
                testGuild.upsertCommand(command).queue();
            } else {
                jda.upsertCommand(command).queue();
            }
        }

    }

    public static SlashCommandManager getCmdManager() {
        return cmdManager;
    }
}
