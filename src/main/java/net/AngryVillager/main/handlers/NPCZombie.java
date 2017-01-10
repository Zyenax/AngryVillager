package net.AngryVillager.main.handlers;

import net.AngryVillager.main.Main;
import net.AngryVillager.main.utils.Packets;
import net.AngryVillager.main.utils.Utils;
import net.minecraft.server.v1_11_R1.EnumParticle;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class NPCZombie implements Listener{
	
	@SuppressWarnings("unused")
	private static Main plugin;

	public NPCZombie(Main hub) {
		NPCZombie.plugin = hub;
		createNPC();
	}
	
	
	Location loc = new Location(Bukkit.getWorld("world"), -6.5, 4, -3.5, -90, 0);
	public void createNPC(){
		Zombie golem = (Zombie) Bukkit.getWorld("world").spawn(loc, Zombie.class);
		golem.setAI(false);
		golem.setCanPickupItems(false);
		golem.setCollidable(false);
		golem.setCustomName(Utils.color("&e&lRight Click Me"));
		golem.setCustomNameVisible(true);
		golem.setInvulnerable(true);
		golem.setSilent(true);
		golem.teleport(loc);
		golem.setGravity(false);
		System.out.println("Zombie MADE 111111111111111111");
	}
	
	@EventHandler
	public void npcInteract(PlayerInteractEntityEvent event){
		if (event.getRightClicked().getType() == EntityType.ZOMBIE) {
			if(event.getRightClicked().getLocation().equals(loc)){
				event.setCancelled(true);
				
			}else{
				event.setCancelled(true);
				
			}
		}
	}
	
	@EventHandler
	public void join(PlayerJoinEvent event){
		Packets.createParticleHelix(event.getPlayer().getLocation(), 3, 2, 0.2, EnumParticle.FIREWORKS_SPARK);
	}
	


}
