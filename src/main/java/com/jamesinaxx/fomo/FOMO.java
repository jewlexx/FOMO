package com.jamesinaxx.fomo;

import com.jamesinaxx.fomo.Discord.Discord;
import com.jamesinaxx.fomo.Minecraft.Lag;
import com.jamesinaxx.fomo.Minecraft.Minecraft;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.Objects;

import static com.jamesinaxx.fomo.Discord.Discord.UpdatePresence;
import static com.jamesinaxx.fomo.Discord.Discord.sendMessage;

public final class FOMO extends JavaPlugin {

  public static FileConfiguration config;

  public static JDA client = null;

  public static TextChannel channel = null;

  @Override
  public void onEnable() {
    // Plugin startup logic

    Bukkit.getPluginManager().registerEvents(new Minecraft(), this);

    Bukkit
      .getServer()
      .getScheduler()
      .scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);

    saveDefaultConfig();
    config = this.getConfig();
    config.addDefault("token", "TOKEN_HERE");
    config.addDefault("channel", "CHANNEL_ID_HERE");
    config.addDefault("prefix", "!");
    saveConfig();
    String botToken = this.getConfig().getString("token");

    if (botToken == null || botToken.equals("TOKEN_HERE") || this.getConfig().getString("bot.channel") == null || this.getConfig().getString("bot.channel").equals("CHANNEL_HERE")) {
      getLogger().severe("Please update FOMO/config.yml");
      Bukkit.getPluginManager().disablePlugin(this);
    }

    try {
      client =
        JDABuilder
          .createLight(botToken, GatewayIntent.GUILD_MESSAGES)
          .addEventListeners(new Discord())
          .build();

      client.awaitReady();

      channel = client.getTextChannelById(config.getLong("channel"));

      UpdatePresence();

      sendMessage("[Minecraft] Server is now running!");

      // Log a message that the connection was successful and log the url that is needed to invite the bot
      Bukkit
        .getLogger()
        .info(
          "[FOMO] Connected to Discord as " +
          Objects.requireNonNull(client.getSelfUser().getAsTag())
        );
    } catch (LoginException | InterruptedException e) {
      e.printStackTrace();
    }

    if (
      Objects.requireNonNull(botToken).isEmpty() ||
      Objects
        .requireNonNull(this.getConfig().getString("channel"))
        .isEmpty()
    ) {
      Bukkit
        .getLogger()
        .severe(
          "Config is not set up properly! Please double check FOMO/config.yml"
        );
      Bukkit.getPluginManager().disablePlugin(this);
    }

    getLogger().info("Successfully initialized FOMO discord bot");

    getLogger().info("Successfully initialized FOMO");
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
    if (client != null) {
      sendMessage("[Minecraft] Server has shut down :(");
      client.shutdown();
      client = null;
    }
    getLogger().info("Successfully shutdown FOMO");
  }
}
