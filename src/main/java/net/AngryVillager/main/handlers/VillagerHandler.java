package net.AngryVillager.main.handlers;

import java.util.HashMap;

import net.AngryVillager.main.Main;
import net.AngryVillager.main.utils.Utils;
import net.minecraft.server.v1_11_R1.EntityInsentient;
import net.minecraft.server.v1_11_R1.PathEntity;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class VillagerHandler implements Listener{
	
	private static Main plugin;

	public VillagerHandler(Main hub) {
		VillagerHandler.plugin = hub;
	}
	
	@EventHandler
	public void onQuit(EntityDamageEvent e){
		e.setCancelled(true);
	}
	
	public static HashMap<Player, Entity> VillagersActive = new HashMap<Player, Entity>();
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = (Player)e.getPlayer();
		Villager villager = (Villager) player.getWorld().spawn(player.getLocation(), Villager.class);
		VillagersActive.put(player, villager);
		villager.setBaby();
		villager.setCanPickupItems(false);
		villager.setCollidable(false);
		villager.setCustomName(Utils.color("&8&l[&e&lBaby " + " &a&l" + player.getName() + "&8&l]"));
		villager.setCustomNameVisible(true);
		villager.setGlowing(true);
		villager.setInvulnerable(true);
		villager.setSilent(true);
		villager.setTarget(null);
		petfollow(player, villager, 0.5);
		player.setCollidable(false);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player player = (Player)e.getPlayer();
		if(VillagersActive.containsKey(player)){
			VillagersActive.get(player).remove();
		}
	}
	
	public static void petfollow(final Player player, final Entity pet, final Double petSpeed) {
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			public void run() {

				if (!pet.isValid()) {
					this.cancel();
					return;
				}

				if (!player.isOnline()) {
					this.cancel();
					pet.remove();
					return;
				}

				EntityInsentient petObject = (EntityInsentient) ((CraftEntity) pet)
						.getHandle();

				Location loc = player.getLocation();

				PathEntity path;

				path = (petObject).getNavigation().a(loc.getX(),
						loc.getY(), loc.getZ());

				if (path != null) {

					(petObject).getNavigation().a(path, petSpeed);
				}

				int distance = (int) loc.distance(pet.getLocation());

				if (distance > 10 && !pet.isDead() && player.isOnGround()) {
					pet.teleport(loc);
				}

			}
		}.runTaskTimer(plugin, 0L, 5L);
	}
	


}
