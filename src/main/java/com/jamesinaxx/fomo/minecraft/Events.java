package com.jamesinaxx.fomo.minecraft;

import com.jamesinaxx.fomo.FOMO;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

import static com.jamesinaxx.fomo.FOMO.jda;

public class Events implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event)  {
        TextChannel channel = jda.getTextChannelById(Objects.requireNonNull(FOMO.config.getString("bot.channel")));
        assert channel != null;
        channel.sendMessage("[Minecraft/"  + event.getPlayer().getDisplayName() + "] " + event.getMessage()).queue();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        TextChannel channel = jda.getTextChannelById(Objects.requireNonNull(FOMO.config.getString("bot.channel")));
        assert channel != null;
        channel.sendMessage("[Minecraft] "  + event.getPlayer().getDisplayName() + " has joined the chat").queue();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        TextChannel channel = jda.getTextChannelById(Objects.requireNonNull(FOMO.config.getString("bot.channel")));
        assert channel != null;
        channel.sendMessage("[Minecraft] "  + event.getPlayer().getDisplayName() + " has left the chat").queue();
    }

}
