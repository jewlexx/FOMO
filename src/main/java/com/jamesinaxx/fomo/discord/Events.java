package com.jamesinaxx.fomo.discord;

import com.jamesinaxx.fomo.FOMO;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class Events extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getName() == FOMO.config.getString("channel")) {
            Bukkit.broadcastMessage("[Discord/" + event.getAuthor().getAsTag() + "] " + event.getMessage().getContentRaw());
        }
    }

}
