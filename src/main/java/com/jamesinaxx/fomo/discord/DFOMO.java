package com.jamesinaxx.fomo.discord;

import com.jamesinaxx.fomo.FOMO;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public class DFOMO {

    private static JDA jda = null;

    public DFOMO(String token) throws LoginException, InterruptedException {

        jda = JDABuilder.createDefault(token).build();

        jda.awaitReady();

        Bukkit.getLogger().info(Color.GREEN + "Successfully initialized FOMO discord bot");
    }

    public static void sendMessage(String msg, String name) {
        assert jda != null;
        TextChannel channel = jda.getTextChannelsByName(Objects.requireNonNull(FOMO.config.getString("bot.channel")), true).get(0);

        channel.sendMessage("["  + name + "] " + msg).queue();
    }
}
