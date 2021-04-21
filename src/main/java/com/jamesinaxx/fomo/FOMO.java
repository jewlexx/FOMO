package com.jamesinaxx.fomo;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public final class FOMO extends JavaPlugin {

    public static FileConfiguration config;

    public static JDA jda = null;

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getPluginManager().registerEvents(new com.jamesinaxx.fomo.minecraft.Events(), this);

        this.saveDefaultConfig();
        config = this.getConfig();
        String botToken = this.getConfig().getString("bot.token");

        try {
            jda = JDABuilder.createDefault(botToken).build();

            jda.awaitReady();

            jda.addEventListener(new com.jamesinaxx.fomo.discord.Events());

            Bukkit.getLogger().info(Color.GREEN + "Successfully initialized FOMO discord bot");
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }

        Bukkit.getLogger().info(Color.GREEN + "Successfully initialized FOMO");

        TextChannel channel = jda.getTextChannelById(Objects.requireNonNull(FOMO.config.getString("bot.channel")));
        assert channel != null;
        channel.sendMessage("[Minecraft] Server is now running!").queue();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        jda.shutdownNow();
        Bukkit.getLogger().info(Color.GREEN + "Successfully shutdown FOMO");
        TextChannel channel = jda.getTextChannelById(Objects.requireNonNull(FOMO.config.getString("bot.channel")));
        assert channel != null;
        channel.sendMessage("[Minecraft] Server has shut down :(").queue();
    }

}
