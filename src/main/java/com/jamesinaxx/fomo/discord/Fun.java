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

import static com.jamesinaxx.fomo.FOMO.jda;

public class Fun {
    public static void sendMessage(String msg, String name) {
        assert jda != null;
        TextChannel channel = jda.getTextChannelsByName(Objects.requireNonNull(FOMO.config.getString("bot.channel")), true).get(0);

        channel.sendMessage("["  + name + "] " + msg).queue();
    }
}
