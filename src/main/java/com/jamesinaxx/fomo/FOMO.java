package com.jamesinaxx.fomo;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import sun.tools.jconsole.JConsole;

import javax.security.auth.login.LoginException;
import java.util.Objects;

import static com.jamesinaxx.fomo.discord.Send.sendMessage;

public final class FOMO extends JavaPlugin {

    public static FileConfiguration config;

    public static JDA jda = null;

    public static TextChannel channel = null;

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

            channel = jda.getTextChannelById(Objects.requireNonNull(FOMO.config.getString("bot.channel")));

            jda.addEventListener(new com.jamesinaxx.fomo.discord.Events());

            Bukkit.getLogger().info("Successfully initialized FOMO discord bot");
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }

        sendMessage("[Minecraft] Server is now running!");

        Bukkit.getLogger().info("Successfully initialized FOMO");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        sendMessage("[Minecraft] Server has shut down :(");
        channel = null;
        jda.shutdown();
        jda = null;
        Bukkit.getLogger().info(Color.GREEN + "Successfully shutdown FOMO");
    }

}
