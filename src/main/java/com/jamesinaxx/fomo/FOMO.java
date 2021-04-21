package com.jamesinaxx.fomo;

import com.jamesinaxx.fomo.minecraft.Events;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info(Color.GREEN + "Successfully shutdown FOMO");
    }

}
