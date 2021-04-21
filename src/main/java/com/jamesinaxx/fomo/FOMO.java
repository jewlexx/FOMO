package com.jamesinaxx.fomo;

import com.jamesinaxx.fomo.discord.DFOMO;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class FOMO extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.saveDefaultConfig();
        String botToken = this.getConfig().getString("bot.token");

        try {
            new DFOMO(botToken);
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
