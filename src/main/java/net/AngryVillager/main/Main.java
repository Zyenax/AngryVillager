package net.AngryVillager.main;

import net.AngryVillager.main.handlers.NPCGolem;
import net.AngryVillager.main.handlers.NPCZombie;
import net.AngryVillager.main.handlers.VillagerHandler;
import net.AngryVillager.main.utils.Packets;
import net.AngryVillager.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	ConsoleCommandSender console = Bukkit.getConsoleSender();
	
	public void onEnable(){
		console.sendMessage(Utils.color("&8[&e&lAngryVillager&8] &aHas been enabled!"));
		for(Entity entity : Bukkit.getWorld("world").getEntities()){
			if(!(entity instanceof Player)){
				entity.remove();
			}
		}
		registerListener();
	}
	
	public void onDisable(){
		console.sendMessage(Utils.color("&8[&e&lAngryVillager&8] &cHas been disabled!"));
	}
	
	public void registerListener(){
		PluginManager manager = Bukkit.getPluginManager();
		manager.registerEvents(new Utils(this), this);
		manager.registerEvents(new NPCGolem(this), this);
		manager.registerEvents(new VillagerHandler(this), this);
		manager.registerEvents(new NPCZombie(this), this);
		manager.registerEvents(new Packets(this), this);
	}
	
	

}
