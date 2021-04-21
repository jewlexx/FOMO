package com.jamesinaxx.fomo.minecraft;

import com.jamesinaxx.fomo.discord.DFOMO;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class Events implements Listener {

    @EventHandler
    public void onMessage(PlayerChatEvent event)  {
        DFOMO.sendMessage(event.getMessage(), event.getPlayer().getDisplayName());
    }

}
