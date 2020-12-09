package com.over2craft.mathchallenge;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Challenge {

    public static boolean playing = false;

    public static Integer term1;
    public static Integer term2;


    public static void play() {

        term1 = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        term2 = ThreadLocalRandom.current().nextInt(1, 100 + 1);

        playing = true;

        Main.plugin.getServer().broadcastMessage(
                String.format(Objects.requireNonNull(Main.plugin.getConfig().getString("challenge_message")), term1.toString(), term2.toString())
        );

    }

    public static Integer getAnswere() {

        if (!playing) {
            throw new RuntimeException("[mathchallenge]  Trying to get challenge answere but no player is playing");
        }

        return term1 + term2;
    }

    public static void setWinner(Player player) {

        if (!playing) {
            throw new RuntimeException("[mathchallenge] Trying to set challenge winner but no player is playing");
        }


        Bukkit.getServer().getScheduler().runTask(Main.plugin, () -> {
            Main.plugin.getEconomy().depositPlayer(player, Main.plugin.getConfig().getInt("challenge_money_reward"));
        });

        Main.plugin.getServer().broadcastMessage(
                String.format(Objects.requireNonNull(Main.plugin.getConfig().getString("won_message")), getAnswere().toString(), player.getDisplayName())
        );

        playing = false;
    }

}
