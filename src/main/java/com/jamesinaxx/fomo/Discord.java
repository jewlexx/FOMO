package com.jamesinaxx.fomo;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;

import javax.security.auth.login.LoginException;
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

    static void ConnectToDiscord(String token) throws LoginException {

        client = JDABuilder
                .createLight(token, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new Discord())
                .build();

        channel = client.getTextChannelById(config.getLong("bot.channel"));

        sendMessage("[Minecraft] Server is now running!");

        client.getPresence().setActivity(Activity.watching(Bukkit.getOnlinePlayers().size()
                + "/"
                + Bukkit.getMaxPlayers() + " playing Minecraft"));

        // Log a message that the connection was successful and log the url that is needed to invite the bot
        Bukkit.getLogger().info("[FOMO] Connected to Discord as " + Objects.requireNonNull(client.getSelfUser().getAsTag()));
    }
}
