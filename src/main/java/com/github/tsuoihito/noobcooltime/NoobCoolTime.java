package com.github.tsuoihito.noobcooltime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public final class NoobCoolTime extends JavaPlugin {

    public long coolTime;
    public final String prefix = "§b[NCT]§f ";

    @Override
    public void onEnable() {
        getCommand("noobcooltime").setExecutor(this);
        getServer().getPluginManager().registerEvents(new NoobBreakListener(this), this);
        
        saveDefaultConfig();
        coolTime = getConfig().getLong("coolTime");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("noobcooltime")) {
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(getPrefix() + getConfig().getString("message.invalidArgument"));
            return true;
        }

        try {
            setCoolTime(Long.parseLong(args[0]));
        } catch (NumberFormatException e) {
            sender.sendMessage(getPrefix() + getConfig().getString("message.invalidValue"));
            return true;
        }

        getConfig().set("coolTime", getCoolTime());
        saveConfig();
        sender.sendMessage(getPrefix() + getConfig().getString("message.setSuccessfully").replace("%coolTime%", Long.toString(getCoolTime())));

        return true;
    }

    @Override
    public void onDisable() {
    }

    public long getCoolTime() {
        return coolTime;
    }

    public void setCoolTime(long coolTime) {
        this.coolTime = coolTime;
    }

    public String getPrefix() {
        return prefix;
    }
}
