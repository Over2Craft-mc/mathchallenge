package com.over2craft.mathchallenge;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import com.over2craft.mathchallenge.constant.Operation;

public class Challenge {

    private static int currentResult;
    private static Operation currentOperation = Operation.NONE;
    private static int term1;
    private static int term2;

    public static boolean isPlaying() {
        return currentOperation != Operation.NONE;
    }

    public static int getCurrentResult() {
        return currentResult;
    }

    public static void play() {
        currentOperation = Operation.findById(ThreadLocalRandom.current().nextInt(0, 3));

        switch(currentOperation) {
            case MULTIPLICATION:
                setResultWithMaxTermValue(10);
                break;
            case SUBTRACTION:
                setResultWithMaxTermValue(50);
                break;
            default:
                setResultWithMaxTermValue(100);
        }
        
        Main.plugin.getServer().broadcastMessage(
                String.format(Objects.requireNonNull(Main.plugin.getConfig().getString("challenge_message")), term1, currentOperation.getSign(), term2)
        );

    }

    private static void setResultWithMaxTermValue(int max) {
        term1 = ThreadLocalRandom.current().nextInt(1, max + 1);
        term2 = ThreadLocalRandom.current().nextInt(1, max + 1);
        
        switch(currentOperation) {
            case MULTIPLICATION:
                currentResult = term1 * term2;
                break;
            case SUBTRACTION:
                currentResult = term1 - term2;
                break;
            case ADDITION:
                currentResult = term1 + term2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentOperation);
        }
    }

    public static void setWinner(Player player) {

        if (!isPlaying()) {
            throw new RuntimeException("[mathchallenge] Trying to set challenge winner but no player is playing");
        }

        int reward;

        switch(currentOperation) {
            case MULTIPLICATION:
                reward = Main.plugin.getConfig().getInt("challenge_money_reward.multiplication");
                break;
            case SUBTRACTION:
                reward = Main.plugin.getConfig().getInt("challenge_money_reward.subtraction");
                break;
            case ADDITION:
                reward = Main.plugin.getConfig().getInt("challenge_money_reward.addition");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentOperation);
        }

        double finalReward = reward + (reward * Main.plugin.getConfig().getDouble("rewardMultiplicatorPerPlayer") * Bukkit.getOnlinePlayers().size());
        Bukkit.getServer().getScheduler().runTask(Main.plugin, () -> {
            Main.plugin.getEconomy().depositPlayer(player, finalReward);
        });

        Main.plugin.getServer().broadcastMessage(
                String.format(Objects.requireNonNull(Main.plugin.getConfig().getString("won_message")),
                        currentResult,
                        player.getDisplayName(),
                        finalReward
                )
        );

        currentOperation = Operation.NONE;
    }

}
