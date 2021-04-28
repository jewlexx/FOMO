package com.jamesinaxx.fomo;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.Objects;

import static com.jamesinaxx.fomo.Discord.ConnectToDiscord;
import static com.jamesinaxx.fomo.Discord.sendMessage;

public final class FOMO extends JavaPlugin {

    public static FileConfiguration config;

    public static JDA client = null;

    public static TextChannel channel = null;

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getPluginManager().registerEvents(new com.jamesinaxx.fomo.Minecraft(), this);

        this.saveDefaultConfig();
        config = this.getConfig();
        String botToken = this.getConfig().getString("bot.token");

        if (Objects.requireNonNull(botToken).isEmpty() || Objects.requireNonNull(this.getConfig().getString("bot.channel")).isEmpty()) {
            Bukkit.getLogger().severe("Config is not set up properly! Please double check FOMO/config.yml");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        try {
            ConnectToDiscord(botToken);
        } catch (LoginException e) {
            e.printStackTrace();
        }

        getLogger().info("Successfully initialized FOMO discord bot");

        getLogger().info("Successfully initialized FOMO");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (client != null) {
            sendMessage("[Minecraft] Server has shut down :(");
            client.shutdownNow();
            client = null;
        }
        getLogger().info(Color.GREEN + "Successfully shutdown FOMO");
    }

}
