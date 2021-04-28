package com.jamesinaxx.fomo.Discord;

import com.jamesinaxx.fomo.FOMO;
import com.jamesinaxx.fomo.Minecraft.Lag;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.bukkit.Bukkit;

import javax.security.auth.login.LoginException;
import java.util.Objects;

import static com.jamesinaxx.fomo.FOMO.*;

public class Discord extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getIdLong() == FOMO.config.getLong("bot.channel")
                && !event.getAuthor().isBot()
                && CommandHandler(event.getMessage())) {
            Bukkit.broadcastMessage("<Discord/" + event.getAuthor().getAsTag() + "> " + event.getMessage().getContentRaw());
        }
    }

    public static void sendMessage(String msg) {
        if (channel != null && client != null) {
            channel.sendMessage(msg).queue();
        }
    }

    private static boolean CommandHandler(Message message) {
        String prefix = Objects.requireNonNull(config.getString("bot.prefix"));
        if (message.getContentRaw().startsWith(prefix)) {
            String command = message.getContentRaw().substring(prefix.length() - 1);

            MessageAction cmdReply = null;

            switch (command) {
                case "test":
                    cmdReply = message.reply("This is kinda a test tbh");
                    break;
                case "online":
                    cmdReply = message.reply(Bukkit.getOnlineMode()
                            + " out of "
                            + Bukkit.getMaxPlayers() + " are online!");
                    break;
                case "tps":
                    if (Objects.requireNonNull(message.getMember()).hasPermission(Permission.ADMINISTRATOR))
                        cmdReply = message.reply("Server TPS is " + Lag.getTPS());
            }

            if (cmdReply != null) {
                cmdReply.queue();
            }

            return false;
        }
        return true;
    }

    public static void ConnectToDiscord(String token) throws LoginException {

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
