package com.jamesinaxx.fomo.discord;

import com.jamesinaxx.fomo.FOMO;
import org.bukkit.Bukkit;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Events implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getChannel().getId() == FOMO.config.getLong("bot.channel") && !event.getMessageAuthor().isBotUser()) {
            Bukkit.broadcastMessage("<Discord/" + event.getMessageAuthor().getDiscriminatedName() + "> " + event.getMessage().getContent());
        }
    }

}
