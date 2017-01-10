package net.AngryVillager.main.handlers;

import net.AngryVillager.main.Main;
import net.AngryVillager.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftFirework;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.IronGolem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class NPCGolem implements Listener{
	
	@SuppressWarnings("unused")
	private static Main plugin;

	public NPCGolem(Main hub) {
		NPCGolem.plugin = hub;
		createNPC();
	}
	
	
	Location loc = new Location(Bukkit.getWorld("world"), -11.5, 4, -3.5, -180, 0);
	public void createNPC(){
		IronGolem golem = (IronGolem) Bukkit.getWorld("world").spawn(loc, IronGolem.class);
		golem.setAI(false);
		golem.setCanPickupItems(false);
		golem.setCollidable(false);
		golem.setCustomName(Utils.color("&e&lRight Click Me"));
		golem.setCustomNameVisible(true);
		golem.setInvulnerable(true);
		golem.setSilent(true);
		golem.teleport(loc);
		golem.setGravity(false);
		System.out.println("GOLEM MADE 111111111111111111");
	}
	
	@EventHandler
	public void npcInteract(PlayerInteractEntityEvent event){
		if (event.getRightClicked().getType() == EntityType.IRON_GOLEM) {
			if(event.getRightClicked().getLocation().equals(loc)){
				event.setCancelled(true);
				Bukkit.getServer().broadcastMessage(Utils.color("&a&lGOLEM IS IN RIGHT LOCATION"));
			}else{
				event.setCancelled(true);
				Bukkit.getServer().broadcastMessage(Utils.color("&c&lGOLEM IS IN WRONG LOCATION"));
			}
		}
	}
	
	public static void playFirework(Location paramLocation,
			FireworkEffect paramFireworkEffect, Integer lifespan) {
		Entity localEntity = paramLocation.getWorld().spawnEntity(
				paramLocation, EntityType.FIREWORK);
		Firework localFirework = (Firework) localEntity;
		FireworkMeta localFireworkMeta = localFirework.getFireworkMeta();
		localFireworkMeta.addEffect(paramFireworkEffect);
		localFireworkMeta.setPower(1);
		localFirework.setFireworkMeta(localFireworkMeta);

		((CraftFirework) localFirework).getHandle().expectedLifespan = lifespan;
	}
	


}
