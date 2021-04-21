package com.jamesinaxx.fomo.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DFOMO {

    private final JDA jda;

    public DFOMO(String token) throws LoginException {

        jda = JDABuilder.createDefault(token).build();

    }
}
