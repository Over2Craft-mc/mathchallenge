package com.over2craft.mathchallenge.listeners;

import com.over2craft.mathchallenge.Challenge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (Challenge.isPlaying()) {
            if (event.getMessage().equals(Challenge.getCurrentResult() + "")) {
                Challenge.setWinner(event.getPlayer());
            }
        }
    }
}