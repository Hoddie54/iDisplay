package com.iCode.idisplay;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

/*
 *  iDisplay made by Hoddie54 & TheHutch
 * 
 *  Give credit when using this source code.
 * 
 */


public class iDisplay extends JavaPlugin{

	Logger log = Logger.getLogger("Minecraft"); 
	protected Configuration config;
	
	
	@Override
	public void onEnable(){
		
		setUpConfig(); //Gets Configuration files
		
		getCommand("ireload").setExecutor(this);
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Type.CUSTOM_EVENT, new iDisplaySpoutListener(this), Priority.Normal, this); //Register Events
		
		config.getString("playerlist", "");
		config.save();
		
	}

	@Override
	public void onDisable() {
		
		log.severe("[iDisplay] is being shut down by admin trololol!");
		
	}
	
	private void setUpConfig(){
		config = getConfiguration();
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String aliases, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("ireload") && (sender.hasPermission("iDisplay.reload") || sender.isOp())){
			log.info("[iDisplay] Initiating Plugin Reload");
			config = getConfiguration();
			config.load(); //Reloads configuration file
			
			
			Player[] pl = getServer().getOnlinePlayers();
			
			for(int i = 0; i < pl.length; i++){	
				
				SpoutPlayer sp = (SpoutPlayer) pl[i];
				if(!sp.isSpoutCraftEnabled()){
					continue;
		    }
				
            Event ev = new SpoutCraftEnableEvent(sp);
	        getServer().getPluginManager().callEvent(ev); //Call the SpoutCraftEnable Event for every SpoutPlayer
	        
		}
			log.info("[iDisplay] Reload Successful");
			sender.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.GREEN+"iDisplay"+ChatColor.DARK_RED+"] has been reloaded");
			
			return true;
	}
		
		return false;
}

	
}
