package com.jamesinaxx.fomo;

import discord4j.core.event.domain.message.MessageCreateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;

import java.util.Objects;

import static com.jamesinaxx.fomo.FOMO.*;

public class Discord {
    public void onMessageCreate(MessageCreateEvent event) {
        String prefix = Objects.requireNonNull(config.getString("bot.prefix"));
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
