package com.jamesinaxx.fomo;

import com.jamesinaxx.fomo.discord.DFOMO;
import com.jamesinaxx.fomo.minecraft.Events;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class FOMO extends JavaPlugin {

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getPluginManager().registerEvents(new Events(), this);

        this.saveDefaultConfig();
        config = this.getConfig();
        String botToken = this.getConfig().getString("bot.token");

        try {
            new DFOMO(botToken);
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
