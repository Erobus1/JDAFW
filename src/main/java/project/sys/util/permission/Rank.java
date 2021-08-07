package project.sys.util.permission;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import project.sys.constants.Config;

import java.util.Arrays;

public enum Rank {
    NONE("none", member -> true),
    MODERATOR("moderator", member -> member.hasPermission(Permission.BAN_MEMBERS)),
    GUILD_ADMIN("administrator", member -> member.hasPermission(Permission.ADMINISTRATOR)),
    GUILD_OWNER("owner", Member::isOwner),
    DEVELOPER("developer", member -> Arrays.asList(Config.getDEVELOPERS()).contains(member.getId()));


    private final String name;
    private final PermCheck callback;
    private final int lvl;

    Rank(String name, PermCheck callback) {
        this.name = name;
        this.callback = callback;
        this.lvl = ordinal();
    }

    public String getName() {
        return name;
    }

    public PermCheck getCallback() {
        return callback;
    }

    public int getLvl() {
        return lvl;
    }
}
