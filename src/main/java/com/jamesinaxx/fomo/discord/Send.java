package com.jamesinaxx.fomo.discord;

import static com.jamesinaxx.fomo.FOMO.channel;
import static com.jamesinaxx.fomo.FOMO.jda;

public class Send {

    public static void sendMessage(String msg) {
        if (channel != null && jda != null) {
                channel.sendMessage(msg).queue();
        }
    }

}
