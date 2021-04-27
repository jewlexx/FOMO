package com.jamesinaxx.fomo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.jamesinaxx.fomo.Discord.sendMessage;

public class Minecraft implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event)  {
        sendMessage("[Minecraft/"  + event.getPlayer().getDisplayName() + "] " + event.getMessage());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        sendMessage("[Minecraft] "  + event.getPlayer().getDisplayName() + " has joined the chat");
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        sendMessage("[Minecraft] "  + event.getPlayer().getDisplayName() + " has left the chat");
    }

}
