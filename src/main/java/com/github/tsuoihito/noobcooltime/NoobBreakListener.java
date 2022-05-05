package com.github.tsuoihito.noobcooltime;

import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class NoobBreakListener implements Listener {

    NoobCoolTime plugin;

    public NoobBreakListener(NoobCoolTime plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent e) {

        if (e.getPlayer().hasPermission("noobcooltime.bypass")) {
            return;
        }

        long playTime = e.getPlayer().getStatistic(Statistic.PLAY_ONE_MINUTE)/20;
        long coolTime = plugin.getCoolTime();

        if (playTime < coolTime) {

            e.getPlayer().sendMessage(plugin.getPrefix() + plugin.getConfig().getString("message.stillCoolTime").replace("%timeLeft%", Long.toString(coolTime - playTime)));
            e.setCancelled(true);

        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockPlace(BlockPlaceEvent e) {

        if (e.getPlayer().hasPermission("noobcooltime.bypass")) {
            return;
        }

        long playTime = e.getPlayer().getStatistic(Statistic.PLAY_ONE_MINUTE)/20;
        long coolTime = plugin.getCoolTime();

        if (playTime < coolTime) {

            e.getPlayer().sendMessage(plugin.getPrefix() + plugin.getConfig().getString("message.stillCoolTime").replace("%timeLeft%", Long.toString(coolTime - playTime)));
            e.setCancelled(true);

        }
    }
}
