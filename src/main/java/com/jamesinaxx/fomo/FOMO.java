package com.jamesinaxx.fomo;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;

import static com.jamesinaxx.fomo.discord.Send.sendMessage;

public final class FOMO extends JavaPlugin {

    public static FileConfiguration config;

    public static DiscordApi client = null;

    public static TextChannel channel = null;

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getPluginManager().registerEvents(new com.jamesinaxx.fomo.minecraft.Events(), this);

        this.saveDefaultConfig();
        config = this.getConfig();
        String botToken = this.getConfig().getString("bot.token");

        new DiscordApiBuilder()
                .setToken(botToken)
                .login()
                .thenAccept(this::onConnectToDiscord)
                .exceptionally(error -> {
                    // Log a warning when the login to Discord failed (wrong token?)
                    getLogger().warning("Failed to connect to Discord! Disabling plugin!");
                    getPluginLoader().disablePlugin(this);
                    return null;
                });

        Bukkit.getLogger().info("Successfully initialized FOMO discord bot");

        sendMessage("[Minecraft] Server is now running!");

        Bukkit.getLogger().info("Successfully initialized FOMO");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (client != null) {
            sendMessage("[Minecraft] Server has shut down :(");
            client.disconnect();
            client = null;
        }
        Bukkit.getLogger().info(Color.GREEN + "Successfully shutdown FOMO");
    }

    private void onConnectToDiscord(DiscordApi api) {
        client = api;

        // Log a message that the connection was successful and log the url that is needed to invite the bot
        getLogger().info("Connected to Discord as " + api.getYourself().getDiscriminatedName());
        getLogger().info("Open the following url to invite the bot: " + api.createBotInvite());

        // Register listeners
        api.addMessageCreateListener(new com.jamesinaxx.fomo.discord.Events());

        channel = api.getChannelById(config.getLong("bot.channel")).get().asTextChannel().get();
    }

}
