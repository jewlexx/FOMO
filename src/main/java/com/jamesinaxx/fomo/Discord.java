package com.jamesinaxx.fomo;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

import java.util.Objects;

import static com.jamesinaxx.fomo.FOMO.*;

public class Discord extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String prefix = Objects.requireNonNull(config.getString("bot.prefix"));
        if (event.getChannel().getIdLong() == FOMO.config.getLong("bot.channel") && !event.getAuthor().isBot()) {
            Bukkit.broadcastMessage("<Discord/" + event.getAuthor().getAsTag() + "> " + event.getMessage().getContentRaw());
        }
    }

    public static void sendMessage(String msg) {
        if (channel != null && client != null) {
            channel.sendMessage(msg).queue();
        }
    }
}
