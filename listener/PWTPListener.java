package net.politwineteampipe.listener;

import net.politwineteampipe.PolitWineTeamPipe;
import net.politwineteampipe.process.MainSmoke;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PWTPListener implements Listener {
    private final PolitWineTeamPipe plugin;
    public PWTPListener(PolitWineTeamPipe plugin) {
        this.plugin = plugin;
    }

    //blaze rod right click | проверка правого клика на огенный стержень
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            if (isHoldingSpecial(player)) {
                MainSmoke.Smoke(player);
            }
        }
    }

    //check item in hand | проверка предмета в руке
    private boolean isHoldingSpecial(Player player) {
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        ItemStack offHandItem = player.getInventory().getItemInOffHand();

        return (isSpecial(mainHandItem) || isSpecial(offHandItem));
    }

    //check item CustomModelData | проверка CustomModelData предмета
    private boolean isSpecial(ItemStack item) {
        if (item.getType() == Material.BLAZE_ROD) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.hasCustomModelData()) {
                int customModelData = meta.getCustomModelData();
                return customModelData == 10011 || customModelData == 10021  || customModelData == 10031  || customModelData == 10041  || customModelData == 10051 || customModelData == 10061;
            }
        }
        return false;
    }

    //cancel use pipe in craft | запрещаем использовать трубку в крафте
    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        for (ItemStack ingredient : event.getInventory().getMatrix()) {
            if (isBanned(ingredient)) {
                event.setCancelled(true);
                break;
            }
        }
    }
    private boolean isBanned(ItemStack item) {
        if (item != null && item.getType() == Material.BLAZE_ROD) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.hasCustomModelData()) {
                int customModelData = meta.getCustomModelData();
                return customModelData == 10011 || customModelData == 10021  || customModelData == 10031  || customModelData == 10041  || customModelData == 10051 || customModelData == 10061;
            }
        }
        return false;
    }
}
