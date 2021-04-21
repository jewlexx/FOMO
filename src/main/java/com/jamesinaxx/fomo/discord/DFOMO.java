package com.jamesinaxx.fomo.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.configuration.file.FileConfiguration;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public class DFOMO {

    private static JDA jda = null;

    public DFOMO(String token) throws LoginException, InterruptedException {

        jda = JDABuilder.createDefault(token).build();

        jda.awaitReady();

    }

    public static void sendMessage(String msg, FileConfiguration config) {
        assert jda != null;
        TextChannel channel = jda.getTextChannelsByName(Objects.requireNonNull(config.getString("bot.channel")), true).get(0);

        channel.sendMessage(msg).queue();
    }
}
