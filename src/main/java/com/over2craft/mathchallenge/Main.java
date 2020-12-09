package com.over2craft.mathchallenge;

import com.over2craft.mathchallenge.listeners.ChatListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {

    private Economy econ;

    static Main plugin;

    @Override
    public void onEnable() {

        plugin = this;

        saveDefaultConfig();

        if (!setupEconomy()) {
            this.getLogger().severe("[mathchallenge] Disabled due to no Vault dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        System.out.println("[mathchallenge] Enabling mathchallenge");;
        getServer().getPluginManager().registerEvents(new ChatListener(), this);

        getCommand("mathchallengereload").setExecutor(
                new CommandExecutor() {
                    @Override
                    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
                        Main.plugin.reloadConfig();
                        commandSender.sendMessage("Plugin reloaded.");
                        return true;
                    }
                }
        );

        new BukkitRunnable() {

            @Override
            public void run() {
                Challenge.play();
            }

        }.runTaskTimer(this, 20 * 60, 20 * this.getConfig().getInt("challenge_interval_in_seconds"));
    }

    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    public Economy getEconomy() {
        return econ;
    }

}
