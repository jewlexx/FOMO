package com.jamesinaxx.fomo.Discord;

import static com.jamesinaxx.fomo.FOMO.*;

import com.jamesinaxx.fomo.FOMO;
import com.jamesinaxx.fomo.Minecraft.Lag;
import java.awt.*;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;

public class Discord extends ListenerAdapter {

  public static long lastPresenceUpdate = System.currentTimeMillis() - 300000;

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    if (
      event.getChannel().getIdLong() == FOMO.config.getLong("bot.channel") &&
      !event.getAuthor().isBot() &&
      CommandHandler(event.getMessage())
    ) {
      Bukkit.broadcastMessage(
        "<Discord/" +
        event.getAuthor().getAsTag() +
        "> " +
        event.getMessage().getContentRaw()
      );
    }
  }

  public static void sendMessage(String msg) {
    if (channel != null && client != null) {
      channel.sendMessage(msg).queue();
    }
  }

  private static boolean CommandHandler(Message message) {
    String prefix = Objects.requireNonNull(config.getString("bot.prefix"));
    if (message.getContentRaw().startsWith(prefix)) {
      String command = message.getContentRaw().substring(prefix.length());

      MessageAction cmdReply = null;

      switch (command) {
        case "online":
          String onlinePlayers =
            Bukkit.getOnlinePlayers().size() +
            " out of " +
            Bukkit.getMaxPlayers() +
            " players are online!";
          EmbedBuilder embedBuilder = new EmbedBuilder();
          cmdReply =
            message.reply(
              embedBuilder
                .setTitle(onlinePlayers)
                .setFooter("FOMO by jamesinaxx")
                .addField(
                  "Online players: ",
                  Bukkit
                    .getOnlinePlayers()
                    .stream()
                    .map(HumanEntity::getName)
                    .collect(Collectors.joining(", ")),
                  false
                )
                .setColor(Color.CYAN)
                .build()
            );
          break;
        case "tps":
          if (
            Objects
              .requireNonNull(message.getMember())
              .hasPermission(Permission.ADMINISTRATOR)
          ) cmdReply =
            message.reply("Server TPS is " + Math.round(Lag.getTPS()));
      }

      if (cmdReply != null) {
        cmdReply.queue();
      }

      return false;
    }
    return true;
  }

  public static void ConnectToDiscord(String token) throws LoginException {
    client =
      JDABuilder
        .createLight(token, GatewayIntent.GUILD_MESSAGES)
        .addEventListeners(new Discord())
        .build();

    channel = client.getTextChannelById(config.getLong("bot.channel"));

    UpdatePresence();

    sendMessage("[Minecraft] Server is now running!");

    // Log a message that the connection was successful and log the url that is needed to invite the bot
    Bukkit
      .getLogger()
      .info(
        "[FOMO] Connected to Discord as " +
        Objects.requireNonNull(client.getSelfUser().getAsTag())
      );
  }

  public static void UpdatePresence() {
    if (System.currentTimeMillis() - lastPresenceUpdate >= 300000) {
      client
        .getPresence()
        .setActivity(
          Activity.watching(
            Bukkit.getOnlinePlayers().size() +
            "/" +
            Bukkit.getMaxPlayers() +
            " playing Minecraft"
          )
        );

      lastPresenceUpdate = System.currentTimeMillis();
    }
  }
}
