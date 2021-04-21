package com.jamesinaxx.fomo.discord;

import org.javacord.api.entity.message.MessageBuilder;

import static com.jamesinaxx.fomo.FOMO.channel;
import static com.jamesinaxx.fomo.FOMO.client;

public class Send {

    public static void sendMessage(String msg) {
        if (channel != null && client != null) {
                new MessageBuilder()
                        .append(msg)
                        .send(channel);
        }
    }

}
