package com.jamesinaxx.fomo;

import discord4j.core.event.domain.message.MessageCreateEvent;
import org.bukkit.Bukkit;

import static com.jamesinaxx.fomo.FOMO.channel;
import static com.jamesinaxx.fomo.FOMO.client;

public class Discord {
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessage().getChannelId().asLong() == FOMO.config.getLong("bot.channel") && !event.getMember().get().isBot()) {
            Bukkit.broadcastMessage("<Discord/" + event.getMember().get().getTag() + "> " + event.getMessage().getContent());
        }
    }

    public static void sendMessage(String msg) {
        if (channel != null && client != null) {
            channel.createMessage(msg).block();
        }
    }
}
