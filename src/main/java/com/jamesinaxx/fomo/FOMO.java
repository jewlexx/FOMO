package com.jamesinaxx.fomo;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static com.jamesinaxx.fomo.Discord.sendMessage;

public final class FOMO extends JavaPlugin {

    public static FileConfiguration config;

    public static GatewayDiscordClient client = null;

    public static MessageChannel channel = null;

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

        ConnectToDiscord(botToken);

        getLogger().info("Successfully initialized FOMO discord bot");

        getLogger().info("Successfully initialized FOMO");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (client != null) {
            sendMessage("[Minecraft] Server has shut down :(");
            client.logout().block();
            client = null;
        }
        getLogger().info(Color.GREEN + "Successfully shutdown FOMO");
    }

    private void ConnectToDiscord(String token) {

        client = DiscordClientBuilder.create(token).build().login().block();

        // Add listener
        assert client != null;
        client.on(MessageCreateEvent.class).subscribe(event -> new com.jamesinaxx.fomo.Discord().onMessageCreate(event));

        channel = (MessageChannel) client.getChannelById(Snowflake.of(config.getLong("bot.channel"))).block();

        sendMessage("[Minecraft] Server is now running!");

        // Log a message that the connection was successful and log the url that is needed to invite the bot
        getLogger().info("Connected to Discord as " + Objects.requireNonNull(client.getSelf().block()).getTag());
    }

}
