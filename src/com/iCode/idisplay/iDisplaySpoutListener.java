package com.iCode.idisplay;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.event.spout.SpoutListener;
import org.getspout.spoutapi.player.AppearanceManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class iDisplaySpoutListener extends SpoutListener {

	public static iDisplay plugin;
	
	public iDisplaySpoutListener(iDisplay instance) {
		plugin = instance;
	}
	
	
	public void onSpoutCraftEnable(SpoutCraftEnableEvent ev){
	
		
		
		SpoutPlayer player = SpoutManager.getPlayer(ev.getPlayer());
		AppearanceManager am = SpoutManager.getAppearanceManager();
		
		
		if(!player.isSpoutCraftEnabled()){
			return;
		}
		if(!player.hasPermission("iDisplay.title") && !player.isOp()){
			player.sendNotification("Permission", "You do not have permission", Material.RED_ROSE);
			return;
		}
		if(player.isDead()){
			return;
		}
		
		
		String newtitle = plugin.config.getString("playerlist." + player.getName(), "I AM NOOB");
		
		
		Player[] players = plugin.getServer().getOnlinePlayers();
		
		for(int i =0; i < players.length; i++){
			
			try{@SuppressWarnings("unused")
			SpoutPlayer p = (SpoutPlayer)players[i];}
			catch(Exception e){continue;}
			
		am.setPlayerTitle((SpoutPlayer)players[i], (LivingEntity)player, newtitle);
		
	}}
	
	
}
