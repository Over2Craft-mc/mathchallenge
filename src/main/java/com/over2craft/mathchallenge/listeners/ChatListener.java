package com.over2craft.mathchallenge.listeners;

import com.over2craft.mathchallenge.Challenge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        if (Challenge.playing) {
            System.out.println(event.getMessage());
            System.out.println(Challenge.getAnswere().toString());
            System.out.println(event.getMessage().equals(Challenge.getAnswere().toString()));
            if (event.getMessage().equals(Challenge.getAnswere().toString())) {
                Challenge.setWinner(event.getPlayer());
            }
        }

    }

}
