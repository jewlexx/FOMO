package com.jamesinaxx.fomo.Minecraft;

import static com.jamesinaxx.fomo.Discord.Discord.UpdatePresence;
import static com.jamesinaxx.fomo.Discord.Discord.sendMessage;

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
      event.getPlayer().getDisplayName() +
      "] " +
      event.getMessage()
    );
    UpdatePresence();
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    sendMessage(
      "[Minecraft] " +
      event.getPlayer().getDisplayName() +
      " has joined the chat"
    );
    UpdatePresence();
  }

  @EventHandler
  public void onPlayerLeave(PlayerQuitEvent event) {
    sendMessage(
      "[Minecraft] " + event.getPlayer().getDisplayName() + " has left the chat"
    );
    UpdatePresence();
  }
}
