package net.politwineteampipe.process;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainSmoke {
    public static Map<UUID, Long> lastInteractTime = new HashMap<>();
    public static void Smoke(Player player) {
        if (player.isOp()) {
            //particle spawn setting | настройки спавна частиц
            Location location = player.getLocation();
            location.setY(location.getY() + 1.5);
            double offsetX = -0.06;
            double offsetY = -0.06;
            double offsetZ = 0;
            double speed = 0.01;
            int count = 3;

            //particle spawn | спавн частиц
            player.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, count, offsetX, offsetY, offsetZ, speed);
            Effects(player);
        } else {
            //delay between smoking | задержка между курением
            UUID uuid = player.getUniqueId();
            long currentTime = System.currentTimeMillis();
            if (lastInteractTime.containsKey(uuid) && currentTime - lastInteractTime.get(uuid) < 900) {
                return;
            }
            lastInteractTime.put(uuid, currentTime);

            //particle spawn setting | настройки спавна частиц
            Location location = player.getLocation();
            location.setY(location.getY() + 1.5);
            double offsetX = -0.06;
            double offsetY = -0.06;
            double offsetZ = 0;
            double speed = 0.01;
            int count = 3;

            //particle spawn | спавн частиц
            player.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, count, offsetX, offsetY, offsetZ, speed);
            Effects(player);
        }
    }

    //give player effects | выдача эффектов игроку
    private static void Effects(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1));
    }
}
