package net.politwineteampipe;

import net.politwineteampipe.cmd.MainCmd;
import net.politwineteampipe.listener.PWTPListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class PolitWineTeamPipe extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PWTPListener(this), this);
        this.getCommand("pwtp").setExecutor(new MainCmd(this));
        getLogger().info(ChatColor.DARK_GREEN + "Плагин PolitWineTeamRocket активирован");
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.DARK_RED + "Плагин PolitWineTeamRocket отключен");
    }
}
