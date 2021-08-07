package project.sys.util.permission;

import net.dv8tion.jda.api.entities.Member;

public interface PermCheck {
    boolean hasPermission(Member member);
}
