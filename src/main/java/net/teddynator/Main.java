package net.teddynator;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import javax.security.auth.login.LoginException;

public class Main {

    public static JDA jda;
    public static String token;

    public static void main(String[] args) throws LoginException {
        token = "";

        jda = JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_MEMBERS).setChunkingFilter(ChunkingFilter.ALL).setMemberCachePolicy(MemberCachePolicy.ONLINE).build();
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("TEST"));

        jda.addEventListener(new News());

        System.out.println("BOT IS ONLINE");
    }


}
