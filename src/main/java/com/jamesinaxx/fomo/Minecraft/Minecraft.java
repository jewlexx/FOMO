package com.jamesinaxx.fomo.Minecraft;

import static com.jamesinaxx.fomo.Discord.Discord.sendMessage;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Minecraft implements Listener {

  @EventHandler
  public void onMessage(AsyncPlayerChatEvent event) {
    sendMessage(
      "[Minecraft/" +
              ChatColor.stripColor(event.getPlayer().getDisplayName()) +
      "] " +
      event.getMessage()
    );
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    sendMessage(
      "[Minecraft] " +
              ChatColor.stripColor(event.getPlayer().getDisplayName()) +
      " has joined the chat"
    );
  }

  @EventHandler
  public void onPlayerLeave(PlayerQuitEvent event) {
    sendMessage(
      "[Minecraft] " + ChatColor.stripColor(event.getPlayer().getDisplayName()) + " has left the chat"
    );
  }
}
