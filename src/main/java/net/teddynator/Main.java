package net.teddynator;

import lombok.Getter;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.configuration.file.YamlConfiguration;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {

    public static JDA jda;
    public static String prefix;
    public static String token;
    public static String activity;
    @Getter
    private static String absoluteConfigPath;


    public static void main(String[] args) throws LoginException {
//        BasicConfigurator.configure();

//        Path currentRelativePath = Paths.get("");
//        String absPath = currentRelativePath.toAbsolutePath().toString();


//        absoluteConfigPath = absPath + File.separatorChar + "config.yml";
//        try {
//            if (!new File(absoluteConfigPath).exists())
//                Files.copy(Objects.requireNonNull(Main.class.getResourceAsStream("/config.yml")), Path.of(absoluteConfigPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }


//        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("config.yml"));

//        token = config.getString("token");

        token = "OTgzNzg3MjcxNzU1MDE4MjUw.GziOVQ.wih52jKqdbL745mV7r6DnVRngPEUL5hCltEQmc";

        jda = JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_MEMBERS).setChunkingFilter(ChunkingFilter.ALL).setMemberCachePolicy(MemberCachePolicy.ONLINE).build();
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        prefix = config.getString("prefix"); //sets prefix from config file (redundant in net.teddynator.Main class)
//        prefix = "/";

        jda.getPresence().setStatus(OnlineStatus.ONLINE); //sets status (Idle, Online, Invisible etc.)

//        activity = config.getString("activity"); //sets activity from config file
        jda.getPresence().setActivity(Activity.playing("TEST"));

        jda.addEventListener(new News());

        System.out.println("BOT IS ONLINE");
    }


}
